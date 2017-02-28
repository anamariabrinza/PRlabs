/**
 * Created by Amy on 2/28/2017.
 */
class Dog extends Animal {

    int age;
    public Dog(int dogAge){

        age = dogAge;

    }

    public void bark(){

        System.out.println("Ham! Ham!");

    }


    public int getAge(){
        return age;
    }

    public static void main(String[] args){
        Dog spike = new Dog(5);
        spike.bark();
        int spikeAge = spike.getAge();
        System.out.println(spikeAge);
        spike.checkStatus();
    }
}
