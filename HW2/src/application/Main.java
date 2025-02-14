package application;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Questions questions = new Questions();
		Answers answers = new Answers();
		int ID = 1;
		
		while (true) {
			System.out.println("\n MENU");
            System.out.println("1. Create a Question");
            System.out.println("2. View All Questions");
            System.out.println("3. Update a Question");
            System.out.println("4. Delete a Question");
            System.out.println("5. Create an Answer");
            System.out.println("6. View Answers for a Question");
            System.out.println("7. Update an Answer");
            System.out.println("8. Delete an Answer");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();
           
            switch(option) {
            case 1:
                System.out.print("Enter your question: ");
                String questionText = scanner.nextLine();
                questions.addQuestion(questionText, ID);
                break;

            case 2:
                questions.QuestionList();
                break;

            case 3:
                System.out.print("Enter question ID to update: ");
                int updateId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new text: ");
                String newText = scanner.nextLine();
                questions.updateQuestion(updateId, newText, ID);
                break;

            case 4:
                System.out.print("Enter question ID to delete: ");
                int deleteId = scanner.nextInt();
                questions.deleteQuestion(deleteId, ID);
                break;

            case 5:
                System.out.print("Enter question ID to answer: ");
                int questionId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter your answer: ");
                String answerText = scanner.nextLine();
                answers.addAnswer(questionId, answerText, ID);
                break;

            case 6:
                System.out.print("Enter question ID to view answers: ");
                int viewAnswersId = scanner.nextInt();
                answers.AnswerList(viewAnswersId);
                break;

            case 7:
                System.out.print("Enter answer ID to update: ");
                int answerUpdateId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new text: ");
                String answerNewText = scanner.nextLine();
                answers.updateAnswer(answerUpdateId, answerNewText, ID);
                break;

            case 8:
                System.out.print("Enter answer ID to delete: ");
                int answerDeleteId = scanner.nextInt();
                answers.deleteAnswer(answerDeleteId, ID);
                break;

            case 9:
                System.out.println("Goodbye.");
                scanner.close();
                return;

            default:
                System.out.println("Error: Invalid choice. Try again.");
                
            }
		}
	}
}
