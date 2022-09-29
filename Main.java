public class Main {
    public static void main(String[] args) {
        RPNCompiler compiler = new RPNCompiler();
        Integer result = compiler.compile("Calc1.stk", true);
        System.out.println(result);
        
    }
}
