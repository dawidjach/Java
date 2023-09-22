package zadania;

public class PrimeNumbers {
    private int number;

    public PrimeNumbers(int number) {
        this.number = number;
        boolean isPrime = true;

        if (number < 2) {
            isPrime = false;
        } else {
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }
    }

    public static void main(String[] args) {
        PrimeNumbers prime1 = new PrimeNumbers(25);
        PrimeNumbers prime2 = new PrimeNumbers(13);
    }
}