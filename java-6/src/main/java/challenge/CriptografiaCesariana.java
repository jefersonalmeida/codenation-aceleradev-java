package challenge;

public class CriptografiaCesariana implements Criptografia {
    private final int secret = 3;
    private final char charMin = 97;
    private final char charMax = 122;

    @Override
    public String criptografar(String value) {
        return encryptOrDecrypt(value, true);
    }

    @Override
    public String descriptografar(String value) {
        return encryptOrDecrypt(value, false);
    }

    private String encryptOrDecrypt(String val, Boolean isEncrypt) throws IllegalArgumentException {
        if (val.isEmpty()) throw new IllegalArgumentException();

        val = val.toLowerCase();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < val.length(); i++) {
            char letter = val.charAt(i);
            if (letter >= charMin && letter <= charMax) {
                letter = isEncrypt ? encrypt(letter) : decrypt(letter);
            }
            builder.append(letter);
        }
        return builder.toString();
    }

    private char encrypt(int i) {
        i += secret;
        return (char) (i > charMax ? ((i % charMax) + charMin - 1) : i);
    }

    private char decrypt(int i) {
        i -= secret;
        return (char) (i < charMin ? (charMax - (secret - (((i + secret) % charMin) + 1))) : i);
    }
}
