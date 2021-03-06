import java.util.Scanner;
import java.io.IOException;
import java.lang.Exception;
import java.util.Random;
import java.util.InputMismatchException;
/**
 * Mud is class that handles the player game interaction
 */
public class Mud 
{   
    private static final String start =  "\n\nYou wake up, you find yourself in a hallway.\nNext to you you find a ragged backpack,\n you pick it up and somehow knows it has volume of 10.\nNow to the big question, which way to go?\n";

    private static final String help = "\nTo know where you are write where am i \nTo move just write the direction. Example west or south.\n\nWrite Pickup item to pickup the item.\nTalk and name to talk to teacher and students\n\nWrite Trade and students name to trade a course book for either an answer or another couse book.\nWrite Enroll to take a course a teacher is teaching.\n\nStatus to get your status\n\nWrite Take degree to complete the game if you got 180hp, no unfinished course and standing in the same room as the sphinx\n";

    //fel input ges
    private  Game game;
    private  Avatar avatar;
    private  Room room;  

    /**
     * Default contructor for the Mud class
     * @throws IOException if files in game canot be found
     */
    public Mud() throws IOException
    {  
        game = new Game(); 
        avatar = game.getAvatar();
    }

    /**
     * Returns the current room the player is standing in
     * @return current room
     */
    public Room getRoom()
    {
        return room; 
    }

    /**
     * Wait for the player to write start and the starts the game
     */
    public void start()
    {   
        
        Scanner input = new Scanner(System.in);
        room = game.begin();
        String answer = input.nextLine().toLowerCase();
        while(!(answer.equals("start"))){
                
            System.out.println("To start the game write start");
            answer = input.nextLine().toLowerCase();
        };
        printStart();
    }

    /**
     * Prints start string one character at a time

     */
    private  void printStart()
    {   
        for (int i = 0; i < start.length(); i++) {
            try{
                System.out.print(start.charAt(i));
                if(start.charAt(i) == '.'|| start.charAt(i) == '?')
                    Thread.sleep(1000);
                if(start.charAt(i) == ',')
                    Thread.sleep(500);
                else
                    Thread.sleep(100);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        System.out.println("\n\n");
    }

    /**
     * Sets room to a new room
     * @param newRoom the newRoom
     */
    public void moveToRoom(String newRoom)
    {
        room = game.getRoom(newRoom);
    }

    /**
     * a while loop that waits for player to decide what do to do.
     * @return either direction the player whises to move in
     * or if the game is complete returns completet
     */
    public String playerAction()
    {
        String answer;
        Scanner input = new Scanner(System.in);
        Creature currentCreature = checkForTeacher();
        String interaction; 
        
        while(true)
        {  
            System.out.println("What do you want to do?");
            answer = input.nextLine();
            answer = answer.toLowerCase();
            String[] words = answer.split(" ");
            interaction = words[0];
            if(isDirection(answer) && game.openDoor(answer,room)){
                //if(currentCreature != null && !(currentCreature instanceof Sfinx)){
                  //  game.placeCreatureRandom(room);
               // }
                return room.getInDirection(answer);   
            }
            else if (answer.equals("help"))
                System.out.println(help);
            else if(room.hasItem() && answer.equals("pickup item"))
            {
                pickupItem();
            }
            else if(answer.equals("status")){
                avatar.printAvatar();
            }
            else if(answer.equals("where am i"))
            {
                System.out.println("\n"+room); 
            }
            else if(currentCreature != null && currentCreature.validInteraction(interaction)){
                if (interactCreature(answer,room))
                    return "Complete";
            }
            else
                System.out.println("Invalid action");
        }

    }

    private Creature checkForTeacher()
    {   Creature creature = null;

        if(room.hasCreature()){
            creature = room.getCreature();
            if(creature instanceof Teacher)
                teacherAskQuestion((Teacher)creature);    
        }
        return creature; 
    }
 
    private  boolean  interactCreature(String answer,Room room)
    {   
        Creature creature = room.getCreature();
        String creatureName = creature.getCreatureName().toLowerCase();
        String interaction  = answer.toLowerCase();

        if(interaction.equals("talk " +creatureName))
            creature.talk();
        else if(answer.equals("enroll") && (creature instanceof Teacher))
            playerEnroll(creature);
        else if(answer.equals("take degree") && (creature instanceof Sfinx))
        {
            if(avatar.checkIfDone())
                return true;
            else
                System.out.println("You can't fool me, shame on you");    
        }
        else if(answer.equals("trade") && (creature instanceof Student))
               game.trade((Student) creature);
        else System.out.println("Invalid action");
        return false;
    }


    private boolean isDirection(String answer)
    {
        return(answer.equals("north")|| 
                answer.equals("east") ||
                answer.equals("south")||
                answer.equals("west"));
    }

    private void teacherAskQuestion(Teacher teacher)
    {   
        Random rand = new Random();
        int random  = rand.nextInt(100); 
        int probability = game.checkEnroll(teacher);

        if(probability > random){
            int ans;

            if(avatar.checkForItem(teacher.getCourseBook())){
                teacher.askQuestionAlternativeRemoved();
                ans =  getAnswer(2);
                 
            }else{ 
                teacher.askQuestion();
                ans = getAnswer(3);
            }
                checkAnswer(ans,teacher);  
        }

    }
    
    private int getAnswer(int alternatives)
    {   
        Scanner input = new Scanner(System.in);
        int answer;


        do{
            answer = 0; 
            try{
                answer = input.nextInt();
            }catch(InputMismatchException e){
                input.next();
            }    
            if(!(answer <= alternatives) || answer < 1)
            System.out.println("Not a valid answer");

        }while(!(answer <=  alternatives) || answer < 1);
        
        return answer;
    }


    private void checkAnswer(int answer,Teacher teacher)
    {
        Course course = teacher.getCourse();
        boolean hasNotFinnished = avatar.checkUnfinnishedCourses(course);
        boolean hasFinnished = avatar.checkFinnishedCourses(course);
        boolean answerCorrect = teacher.checkAnswer(answer);

        if(!answerCorrect){
            if(hasFinnished){
                System.out.println("Wrong answer, I can´t belive you finnished this Course\n You have to take it again!");
                avatar.removeFinnishedCourse(course);
                avatar.addUnfinnishedCourse(course);
            }
            else System.out.println("Wrong answer");
        }
        if(answerCorrect){
            if(hasNotFinnished){
                System.out.println("Right answer you are now done with this course");    
                avatar.removeUnfinnishedCourse(course);
                avatar.addFinnishedCourse(course);
            }
            else System.out.println("You got the right answer");
        }

    }
    
    private void playerEnroll(Creature teacher)
    {  
        if(game.checkEnroll((teacher)) == 0){
            avatar.addUnfinnishedCourse(teacher.getCourse());
            System.out.println("\nYou are now taking " + teacher.getCourse());
        }
        else if(game.checkEnroll(teacher) == 50)
            System.out.println("You have already finnish this course");
        else
            System.out.println("You are already taking this course");
    }

    
    /**
     * Checks if the player can pickup an item if so puts it in the backpack.
     * If the player dont have room for the item asks if the player want to throw items away.
     * The player will throw items away until the player have room for the new item 
     * 
     */
    public void pickupItem()
    {
        Item item = room.getItem();
        boolean sucess = avatar.addItemBackpack(item); 

        if(sucess)
            System.out.println("You have put " + item + " in your backpack");

        else if(!avatar.checkForItem(item))
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Your backpack is too full for that item\n" );
            avatar.printBackpack();
            while(!sucess)
            {
                System.out.println("Would you like to throw out an item?Yes/No");
                String answer = input.next().toLowerCase();
                if(answer.equals("no"))
                    return;
                else if(answer.equals("yes"))
                    sucess = removeItem();            
            }
            if(sucess)
                System.out.println("You have put " + item + " in your backpack");


        }
        room.setItem(null);
        return;


    }

    private boolean removeItem()
    {   
        Scanner input = new Scanner(System.in);
        int answerRemove = 0;
        Item itemToRemove = null; 
        double packVolume;
        double roomItemVolume;
        double itemToRemoveVolume;
        while(true)
        {
            System.out.println("Which item would you like to remove?(write number)");
            answerRemove = input.nextInt();
            itemToRemove = avatar.getItem(answerRemove - 1);
            packVolume = avatar.getBackpackVolume();
            roomItemVolume = room.getItem().getVolume();
            itemToRemoveVolume = itemToRemove.getVolume();
            
            
            if(packVolume - roomItemVolume + itemToRemoveVolume >= 0)
            {
                avatar.removeItemBackpack(itemToRemove);
                avatar.addItemBackpack(room.getItem());
                room.setItem(itemToRemove);
                return true;
            }
            else
            {
                avatar.removeItemBackpack(itemToRemove);
                System.out.println("You have removed a too small item and need to remove another. The removed item has mysticly been teleported to a random room as punishment");
                game.placeItemRandom(itemToRemove);
                return false;
            }
        }
    }

} 
