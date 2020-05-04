package kg.sanjyra.util.startPersonFilling;

public class RussianToEnglishParser {

    public static String getParsedWord(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            char parsedChar = getParsedChar(character);
            if (parsedChar == Character.MIN_VALUE) {
                switch (character) {
                    case 'Ц':
                        stringBuilder.append("ts");
                        break;
                    case 'Ч':
                        stringBuilder.append("tsh");
                        break;
                    case 'Ш':
                        stringBuilder.append("sh");
                        break;
                    case 'Щ':
                        stringBuilder.append("sh");
                        break;
                    case 'Ю':
                        stringBuilder.append("yu");
                        break;
                    case 'Я':
                        stringBuilder.append("ya");
                        break;
                    case ' ':
                        stringBuilder.append(" ");
                        break;
                }
            } else {
                stringBuilder.append(parsedChar);
            }
        }
        return stringBuilder.toString();
    }

    private static char getParsedChar(char russianCharacter) {
        switch (russianCharacter) {
            case 'А':
                return 'a';
            case 'Б':
                return 'b';
            case 'В':
                return 'v';
            case 'Г':
                return 'g';
            case 'Д':
                return 'd';
            case 'Е':
                return 'e';
            case 'Ё':
                return 'e';
            case 'Ж':
                return 'j';
            case 'З':
                return 'z';
            case 'И':
                return 'i';
            case 'Й':
                return 'y';
            case 'К':
                return 'k';
            case 'Л':
                return 'l';
            case 'М':
                return 'm';
            case 'Н':
                return 'n';
            case 'О':
                return 'o';
            case 'П':
                return 'p';
            case 'Р':
                return 'r';
            case 'С':
                return 's';
            case 'Т':
                return 't';
            case 'У':
                return 'u';
            case 'Ф':
                return 'f';
            case 'Х':
                return 'h';
            case 'Ы':
                return 'y';
            case 'Э':
                return 'e';
            default:
                return Character.MIN_VALUE;
        }

    }
}
