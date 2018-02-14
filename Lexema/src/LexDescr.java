public class LexDescr
{
    private static String type;
    private static String lex;

    LexDescr(String _type, String _lex)
    {
        type = _type;
        lex = _lex;
    }

    public String getType(){
        return type;
    }

    public String getData(){
        return lex;
    }
}
