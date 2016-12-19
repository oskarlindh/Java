public class StatDym
{   
    private static  Animal an; 
    private static  Cat cat; 
    
    public static void main(String[] args) {
        
        an   = new Cat();
        cat  = new Cat();
        hej(an);
        hej(cat);
        hello(an);
        hello(cat);
        System.out.println("\nIn cat:");
        cat.hej(an);
        cat.hej(cat);
    }
        
    private static void hej(Animal an)
    {
        an.talk();
    }
    

    private static  void hej(Cat cat)
    {
        cat.talk();
    }

    private static void hello(Animal an)
    {
        System.out.println("Animal");
    }
    

    private static void hello(Cat cat)
    {
        System.out.println("Cat");
    }




}
