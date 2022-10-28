/* *******************************************************************
 * Copyright (c) 2021 Universidade Federal de Pernambuco (UFPE).
 * 
 * This file is part of the Compilers course at UFPE.
 * 
 * During the 1970s and 1980s, Hewlett-Packard used RPN in all 
 * of their desktop and hand-held calculators, and continued to 
 * use it in some models into the 2020s. In computer science, 
 * reverse Polish notation is used in stack-oriented programming languages 
 * such as Forth, STOIC, PostScript, RPL and Joy.
 *  
 * Contributors: 
 *     Henrique Rebelo      initial design and implementation 
 *     http://www.cin.ufpe.br/~hemr/
 * ******************************************************************/

package postfix.interpreter;

import postfix.ast.Expr;
import java.util.*;

/**
 * @author Henrique Rebelo
 */
public class Interpreter implements Expr.Visitor<Integer> {
	HashMap<String, Integer> variables;
	public Interpreter(HashMap<String, Integer> variables) {
		this.variables = variables;
	}
	public int interp(Expr expression) { 
		int value = evaluate(expression);	
		return value;
	}

	@Override
	public Integer visitNumberExpr(Expr.Number expr) {
		Boolean isNum;
		int result;
		try {
			isNum = true;
			result = Integer.parseInt(expr.value);
		}
		catch(NumberFormatException e) {
			isNum = false;
			result = 0;
		}

		if (!isNum) {
			try {
				result = this.variables.get(expr.value);
			}
			catch(NullPointerException error) {
				throw new NoSuchElementException("Variable " + expr.value + " is not defined");
			}

		}

		return result;
		
		
	}

	@Override
	public Integer visitBinopExpr(Expr.Binop expr) {

		int left = evaluate(expr.left);
		int right = evaluate(expr.right); 
		int result = 0;

		switch (expr.operator.type) {
		case PLUS:
			result = right + left;
			break;
		case MINUS:
			result = right - left;
			break;
		case SLASH:
			result = right / left;
			break;
		case STAR:
			result = right * left;
			break;			
		default:
			break;
		}

		return result;
	}

	private int evaluate(Expr expr) {
		return expr.accept(this);
	}
}
