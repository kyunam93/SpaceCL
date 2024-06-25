package chapter15;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ExternalizableTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Dog myDog = new Dog();
		myDog.name = "멍멍이";

		FileOutputStream fos = new FileOutputStream("external.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		try {
			oos.writeObject(myDog);
		} // end try

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

		FileInputStream fis = new FileInputStream("external.out");
		ObjectInputStream ois = new ObjectInputStream(fis);

		Dog dog = (Dog) ois.readObject();
		System.out.println(dog);

	}// end main

}// end class

class Dog implements Externalizable {
	String name;

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
	}// end method

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
	}// end method

	public String toString() {
		return name;
	}// end method

};// end class
