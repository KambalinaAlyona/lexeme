public class Calculater
{
    private static LexDescr next_lex = null;
    private static Lexer lexer = null;

    public static void main(String [] args)
    {
        int result;

        lexer = new Lexer("input.txt");
        next_lex = lexer.getNextLex();

        try
        {
            result = parsExsp();
            if (next_lex.getType() != "end") {
                throw new Exception("Bad input");
            }

            System.out.println("result: " + result);
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    private static int parsExsp()throws Exception
    {
        int sign;
        int result;
        String cur_type;

        if(next_lex.getType() != "+" && next_lex.getType() != "-")
        {
            result = parsTerm();
        }else {
            result = 0;
        }

        cur_type = next_lex.getType();

        while(cur_type == "+" || cur_type == "-")
        {
            if(cur_type == "+") sign = 1; else sign = -1;

            next_lex = lexer.getNextLex();

            result = result + sign * parsTerm();

            cur_type = next_lex.getType();
        }
        return result;
    }

    private static int parsTerm()throws Exception
    {
        int result;
        String cur_type;

        result = parsFact();

        cur_type = next_lex.getType();

        while(cur_type == "*" || cur_type == "/")
        {
            next_lex = lexer.getNextLex();

            if(cur_type == "*")
                 result = result * parsFact();
            else result = result / parsFact();

            cur_type = next_lex.getType();
        }
        return result;
    }

    private static int parsFact()throws Exception{
        int result;
        String cur_type;

        result = parsPow();

        cur_type = next_lex.getType();
        if (cur_type == "^")
        {
            next_lex = lexer.getNextLex();
            result = powInt(result, parsFact());
        }

        return result;
    }

    private static int parsPow()throws Exception
    {
        int result;
        String cur_type = next_lex.getType();

        if (cur_type == "-")
        {
            next_lex = lexer.getNextLex();
            result = (-1) * parsAtom();
        }
        else
        {
            result = parsAtom();
        }
        return result;
    }

    private static int parsAtom()throws Exception{
        int result;
        String cur_type = next_lex.getType();

        if (cur_type == "number")
        {
            result = Integer.parseInt(next_lex.getData());
        }else
            {
            if(cur_type == "(")
            {
                next_lex = lexer.getNextLex();
                result = parsExsp();
                if(next_lex.getType() != ")")
                {
                    throw new Exception("Error in parsAtom(): have not ')'");
                }
            }
            else
            {
                throw new Exception("Error in parsAtom(): it is not numeric but not '('");
            }
        }
        next_lex = lexer.getNextLex();
        return result;
    }

    private static int powInt(int arg, int deg){
        int res = 1;

        if(deg >= 0 )
        {
            for (int i = 0; i < deg; ++i) {
                res = res * arg;
            }
        }else {
            deg = -deg;

            for (int i = 0; i < deg; ++i) {
                res = res / arg;
            }
        }
        return res;
    }
}
