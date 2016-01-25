package ua.kiev.univ.cyb.command;

/**
 * Helper class to use in commands. Stores page name, that will be displayed later and
 * method of retrieving this page - redirecting or forwarding.
 */
public class Page {
    /**
     * Page that will be displayed later.
     */
    private String page;

    /**
     * True if user will be redirected or false if forwarded.
     */
    private boolean redirected;

    public Page() {
    }

    public Page(String page, boolean redirected) {
        this.page = page;
        this.redirected = redirected;
    }

    /**
     * @return page to display.
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page page to display.
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return true if user will be redirected, false otherwise.
     */
    public boolean isRedirected() {
        return redirected;
    }

    /**
     * @param redirected sets method of displaying page.
     */
    public void setRedirected(boolean redirected) {
        this.redirected = redirected;
    }
}
