import java.util.concurrent.TimeUnit;

public class Payment {
    //Attributes of the Payment class
    int creditCardNo;
    int CVC;
    String userName;

    //Constructor
    public Payment(Integer creditCardNo, Integer CVC, String userName) {
        this.creditCardNo = creditCardNo;
        this.CVC = CVC;
        this.userName = userName;
    }

    //Checks the credit card informations are suitable to generic informations
    public boolean checkCard() {
        if(creditCardNo != 0 && CVC != 0 && !userName.isEmpty() && creditCardNo > 0 && CVC > 0) {
            System.out.println("Your credit card is accepted !!!");
            return true;
        }
        else {
            System.out.println("Please check your credit card information !!!");
            return false;
        }
    }

    //Executes payment
    public boolean pay() throws InterruptedException {
        if(checkCard()) {
            System.out.println("Your Payment is accepted !!!");
            System.out.println("Please wait...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Payment is done !!!");
            return true;
        }
        else {
            System.out.println("Please correct your credit card information !!!");
            return false;
        }
    }
}
