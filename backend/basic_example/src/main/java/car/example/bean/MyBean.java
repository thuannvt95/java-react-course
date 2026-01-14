package car.example.bean;

public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMessage() {
        System.out.println(this.message);
    }

    @Override
    public String toString() {
        return "myBean { message: `" + this.message + "`}";
    }
}
