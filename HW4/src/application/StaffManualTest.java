package application;

public class StaffManualTest {

    public static void main(String[] args) {
        StaffController controller = new StaffController();

        // Test 1: Flagging Content
        controller.flagContent("Q123");
        if (controller.getFlagged().contains("Q123")) {
            System.out.println("✅ Test 1 Passed: Flag Q123 added.");
        } else {
            System.out.println("❌ Test 1 Failed: Q123 not flagged.");
        }

        // Test 2: Deleting Content
        boolean deleted = controller.deleteContent("A456");
        if (deleted && controller.getDeleted().contains("A456")) {
            System.out.println("✅ Test 2 Passed: A456 deleted.");
        } else {
            System.out.println("❌ Test 2 Failed: A456 not deleted properly.");
        }

        // Test 3: Double Deletion
        controller.deleteContent("Q789");
        boolean doubleDelete = controller.deleteContent("Q789");
        if (!doubleDelete) {
            System.out.println("✅ Test 3 Passed: Double delete of Q789 prevented.");
        } else {
            System.out.println("❌ Test 3 Failed: Q789 deleted twice.");
        }

        // Test 4: Multiple Flags
        controller.flagContent("X001");
        controller.flagContent("X002");
        if (controller.getFlagged().size() == 3) {
            System.out.println("✅ Test 4 Passed: Total 3 items flagged.");
        } else {
            System.out.println("❌ Test 4 Failed: Incorrect number of flagged items.");
        }

        // Test 5: Multiple Deletes
        controller.deleteContent("Y001");
        controller.deleteContent("Y002");
        if (controller.getDeleted().size() == 4) {
            System.out.println("✅ Test 5 Passed: Total 4 items deleted.");
        } else {
            System.out.println("❌ Test 5 Failed: Incorrect number of deleted items.");
        }
    }
}
