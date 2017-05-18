/**
 * Created by Amy on 2/28/2017.
 */
class Cat extends Animal {

    public void miau(){
        System.out.println("Miau! Miau!");
    }

    public static void main(String[] args ){
        Cat mary = new Cat();
        mary.miau();
        mary.checkStatus();
    }
}
