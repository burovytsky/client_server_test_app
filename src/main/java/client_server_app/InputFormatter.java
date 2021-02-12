package client_server_app;

/**
 * String formatting according to the specified rules.
 */
public class InputFormatter {

    /**
     * Create answer string.
     *
     * @param input the input
     * @return the string
     */
    public String createAnswer(String input) {
        StringBuilder rsl = new StringBuilder();
        rsl.append("Server answer: ");
        if (input.matches("[-+]?\\d+")) {
            rsl.append(Integer.parseInt(input) * 1000);
        } else {
            rsl.append(toCamelCase(input));
        }
        return rsl.toString();
    }

    private StringBuilder toCamelCase(String input) {
        StringBuilder rsl = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                chars[i] = '_';
            }
            if ((i % 2 == 0)) {
                rsl.append(Character.toUpperCase(chars[i]));
            } else {
                rsl.append(Character.toLowerCase(chars[i]));
            }
        }
        return rsl;
    }
}
