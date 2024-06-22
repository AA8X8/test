package library.items;

public class NonFictionBook extends Book{

    private String subjectArea;
    public NonFictionBook(String title, int year, boolean available,
                          String author, String ibsn, String subjectArea,
                          double cost) {
        super(title, year, available, author, ibsn, cost);
        setSubjectArea(subjectArea);
    }

    public void setSubjectArea(String subjectArea) {
        if (subjectArea.isEmpty()) {
            throw new IllegalArgumentException("Не передана предметная область книги");
        }
        this.subjectArea = subjectArea;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    @Override
    public String getBookDetails() {
        return super.getBookDetails() + ", subject area: " + subjectArea;
    }
}
