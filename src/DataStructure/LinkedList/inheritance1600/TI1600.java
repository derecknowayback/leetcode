package DataStructure.LinkedList.inheritance1600;

import java.util.ArrayList;
import java.util.List;

public class TI1600 {

}
class ThroneInheritance {

    class Person{
        String name;
        List<Person> sons = new ArrayList<>();
        Person father = null;
        boolean isAlive = true;
        public Person(String name) {
            this.name = name;
        }
    }

    Person king;



    public ThroneInheritance(String kingName) {
        king = new Person(kingName);
    }

    public void birth(String parentName, String childName) {

    }

    public void death(String name) {

    }

//    public List<String> getInheritanceOrder() {
//
//    }
}
