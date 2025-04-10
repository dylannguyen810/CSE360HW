package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Backend logic for Staff functions: flagging and deleting content.
 */
public class StaffController {

    private List<String> flaggedContent;
    private List<String> deletedContent;

    public StaffController() {
        flaggedContent = new ArrayList<>();
        deletedContent = new ArrayList<>();
    }

    public void flagContent(String contentId) {
        if (!flaggedContent.contains(contentId)) {
            flaggedContent.add(contentId);
            System.out.println("[Controller] Flagged: " + contentId);
        }
    }

    public boolean deleteContent(String contentId) {
        if (!deletedContent.contains(contentId)) {
            deletedContent.add(contentId);
            System.out.println("[Controller] Deleted: " + contentId);
            return true;
        }
        return false;
    }

    public List<String> getFlagged() {
        return flaggedContent;
    }

    public List<String> getDeleted() {
        return deletedContent;
    }
}