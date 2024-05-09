package JokeServer;

import JokeServer.Administrator.AddAdmin.addAdminImpl;
import JokeServer.Category.Service.CategoryServiceImpl;
import JokeServer.Joke.Service.JokeServiceImpl;
import JokeServer.JokeVerifier.Verifier.VerifierService;
import JokeServer.User.Dao.UserDaoImpl;
import JokeServer.User.Service.UserServiceImpl;

import java.util.Scanner;

public class View {

    private UserServiceImpl userService = new UserServiceImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    private JokeServiceImpl jokeService = new JokeServiceImpl();
    private addAdminImpl addAdmin = new addAdminImpl();
    private VerifierService verifierService = new VerifierService();
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();
    private Scanner input = new Scanner(System.in);

    public void run(){
        boolean running = true;
        System.out.println("Welcome to the Joke Server!!!");
        while (running){
            System.out.println("\n1. Register user");
            System.out.println("2. User login");
            System.out.println("3. Delete user");
            System.out.println("4. Admin login");

            System.out.print("\nEnter your choice: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice){
                case 1:
                    userService.addUser();
                    break;
                case 2:
                    userService.login();

                    boolean looping = true;

                    while (looping){
                        System.out.println("\n1. Add joke");
                        System.out.println("2. Retrieve joke");
                        System.out.println("3. Update joke");
                        System.out.println("4. Delete joke");
                        System.out.println("5. Add category");
                        System.out.println("6. Delete category");
                        System.out.println("7. Exit");

                        System.out.print("\nEnter your choice: ");
                        int choice2 = Integer.parseInt(input.nextLine());

                        switch (choice2){
                            case 1:
                                jokeService.addJoke();
                                break;
                            case 2:
                                jokeService.retrieveAJoke();
                                break;
                            case 3:
                                jokeService.updateJoke();
                                break;
                            case 4:
                                jokeService.deleteJoke();
                                break;
                            case 5:
                                categoryService.addCategory();
                                break;
                            case 6:
                                categoryService.deleteCategory();
                                break;
                            case 7:
                                looping = false;
                                System.out.println("\nExiting.......................................");
                                running = false;
                                userDao.closeConnection();
                                break;
                            default:
                                System.out.println("\nInvalid input!!! Please try again!!!");
                                break;
                        }
                        if (!looping){
                            break;
                        }
                    }
                    break;
                case 3:
                    userService.deleteUser();
                    break;
                case 4:
                    addAdmin.Adminlogin();

                    System.out.println("\n1. Add words to verifier");
                    System.out.println("2. Exit");

                    System.out.print("\nEnter your choice: ");
                    int choice3 = Integer.parseInt(input.nextLine());

                    switch (choice3){
                        case 1:
                            verifierService.addWord();
                            break;
                        case 2:
                            System.out.println("\nExiting.......................................");
                            running = false;
                            userDao.closeConnection();
                            break;
                        default:
                            System.out.println("\nInvalid input!!! Please try again!!!");
                            break;
                    }
                default:
                    System.out.println("\nInvalid input!!! Please try again!!!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new View().run();
    }
}
