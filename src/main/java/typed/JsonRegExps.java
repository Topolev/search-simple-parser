package typed;


public class JsonRegExps {

    // Exponential part of number in scientific notation, for example "10e-10" "10E+10" "10E1"
    private static final String EXP = "([Ee][+-]?+[0-9]++)";

    public static final String NUMERIC_LITERAL = "(?:"
            // Decimal
            + "-?+[0-9]++\\.([0-9]++)?+" + EXP + "?+"
            // Decimal
            + "|-?+[0-9]++" + EXP

            // Integer Literals
            // Hexadecimal
            + "|-?+0[xX][0-9a-fA-F]++"
            // Decimal and octal
            + "|-?+[0-9]++"
            + ")";

    public static final String STRING_LITERAL = "(?:\"([^\"\\\\]*+(\\\\[\\s\\S])?+)*+\")";

    public static final String WORD_LITERAL = "(?:[\\w]+(?==)|(?<![\\S\"])([^=\"\\d\\s][^=\"\\s]+)(?![\\S\"]))";


    public static final String MULTILINE_STRING_LITERAL = "<<([\\w]+)([\\w\\s]*?)\\1";
}
