import java.util.Scanner;
public class RSA_project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Two distinct upper limits for p , q:");
        System.out.print("Upper limit for p:");
        int p = sc.nextInt();
        System.out.print("Upper limit for q:");
        int q = sc.nextInt();

        while (!IsPrime(p)) {
            p--;
        }
        while (!IsPrime(q)) {
            q--;
        }

        int n = p*q;
        int m = (p-1)*(q-1);
        int e = 2;
        while (Gcd(m, e)!=1) {
            e++;
        }
        int d = find_d(m,e);

        System.out.print("Enter Original Message:");
        sc.nextLine();
        String message = sc.nextLine();

        String cipher = Encryption(message, e, n);
        System.out.println("Cipher Code: " + cipher);

        String decryptedMessage = Decryption(cipher, d, n);
        System.out.println("Decrypted Message: " + decryptedMessage);

        sc.close();
    }
    static boolean IsPrime(int prime) {
        if (prime < 2) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(prime); i++) {
            if (prime%i==0) return false;
        }
        return true;
    }
    static int Gcd(int a, int b) {
        if (b == 0) return a;
        return Gcd(b, a % b);
    }
    static int find_d(int m, int e) {
        int k = 1;
        while (((k*m+1.0)/e)!=(k*m+1)/e) k++;
        return (k*m+1)/e;
    }
    static String Encryption(String message, long e, long n) {
        StringBuilder cipher = new StringBuilder();
        for (char ch : message.toCharArray()) {
            long m = (long) ch;
            long c = modularExponentiation(m, e, n);
            cipher.append(c).append(" ");
        }
        return cipher.toString().trim();
    }
    static String Decryption(String cipher, long d, long n) {
        StringBuilder plainText = new StringBuilder();
        String[] cipherValues = cipher.split(" ");
        for (String value : cipherValues) {
            long c = Long.parseLong(value);
            long m = modularExponentiation(c, d, n);
            plainText.append((char) m);
        }
        return plainText.toString();
    }
    static long modularExponentiation(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp%2 == 1) {
                result = result*base % mod;
            }
            exp/=2;
            base = base*base % mod;
        }
        return result;
    }
}