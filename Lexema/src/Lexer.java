import java.io.*;

class Lexer
{
    private Reader reader = null;
    private char next_char = ' ';

    Lexer(String file_name)
    {
        try
        {
            reader = new InputStreamReader(new FileInputStream(file_name));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found :((");
        }
    }

    public LexDescr getNextLex()
    {
        char cur_lex;
        LexDescr new_lex = null;

        try {
            if(next_char != ' ')
            {
                cur_lex = next_char;
                next_char = ' ';
            }
            else
            {
                while ((cur_lex = (char) reader.read()) == ' ') ;
            }

            switch (cur_lex){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8': // Read number
                case '9':
                    String dig = Character.toString(cur_lex);
                    while((next_char = (char)reader.read()) >= '0' && next_char <= '9'){
                        dig = dig + Character.toString(next_char);
                    }
                    new_lex = new LexDescr("number", dig);
                    break;

                case '(':
                    new_lex = new LexDescr("(", Character.toString(cur_lex));
                    break;

                case ')':
                    new_lex = new LexDescr(")", Character.toString(cur_lex));
                    break;

                case '+':
                    new_lex = new LexDescr("+", Character.toString(cur_lex));
                    break;

                case '-':
                    new_lex = new LexDescr("-", Character.toString(cur_lex));
                    break;

                case '*':
                    new_lex = new LexDescr("*", Character.toString(cur_lex));
                    break;

                case '/':
                    new_lex = new LexDescr("/", Character.toString(cur_lex));
                    break;

                case '^':
                    new_lex = new LexDescr("^", Character.toString(cur_lex));
                    break;

                case 'e':
                    new_lex = new LexDescr("end", Character.toString(cur_lex));
                    break;

                default:
                    throw new IOException("not correct");
            }
        }
        catch (IOException e){
        System.out.println("File not found when we read");
        }
        return new_lex;
    }
}
