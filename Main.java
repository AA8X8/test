package library.main;


import library.LibraryManagementSystem;
import library.items.Book;
import library.items.FictionBook;
import library.items.LibraryItem;
import library.items.NonFictionBook;
import library.persons.Professor;
import library.persons.Reader;
import library.persons.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String path = "resources//library.txt";
    static void write(List text) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(text.toString());
            writer.write(System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();

//        Book book = new Book("ds", 1900, true, "sff", "sd", 900);
//        LocalDate date = LocalDate.of(2023, 12, 25);
//        LocalDate date1 = LocalDate.of(2024, 1, 26);
//        System.out.println(book.calculateFineForDays(date, date1));

        System.out.println("""
                Введите номер пункта для реализации функции:
                1. addBook
                2. removeBook
                3. updateBook
                4. addReader
                5. removeReader
                6. updateReader
                7. borrowItem
                8. returnItem
                9. searchLibraryItems
                10. searchReader""");

        String command = scanner.nextLine();

        while (!command.equals("stop")) {
            switch (command) {
                case "1" -> {
                    String title = scanner.nextLine();
                    int year = Integer.parseInt(scanner.nextLine());
                    boolean available = Boolean.parseBoolean(scanner.nextLine());
                    String author = scanner.nextLine();
                    String ibsn = scanner.nextLine();
                    double cost = Double.parseDouble(scanner.nextLine());
                    int position = Integer.parseInt(scanner.nextLine());   // copies
                    String fiction = scanner.nextLine();

                    if (fiction.equalsIgnoreCase("fiction")) {
                        String genre = scanner.nextLine();
                        Book book1 = new FictionBook(title, year, available, author, ibsn, genre, cost);
                        libraryManagementSystem.addBook(book1, position);
                    } else if (fiction.equalsIgnoreCase("non fiction")) {
                        String subjectArea = scanner.nextLine();
                        Book book1 = new NonFictionBook(title, year, available, author, ibsn,
                                subjectArea, cost);
                        libraryManagementSystem.addBook(book1, position);
                    } else throw new IllegalArgumentException("Неправильно введён тип литературы");
                }
                case "2" -> {
                    String title = scanner.nextLine();
                    int year = Integer.parseInt(scanner.nextLine());
                    boolean available = Boolean.parseBoolean(scanner.nextLine());
                    String author = scanner.nextLine();
                    String ibsn = scanner.nextLine();
                    double cost = Double.parseDouble(scanner.nextLine());
                    String fiction = scanner.nextLine();
                    String genre = "";
                    String subjectArea = "";

                    if (fiction.equalsIgnoreCase("fiction")) {
                        genre = scanner.nextLine();
                    } else if (fiction.equalsIgnoreCase("non fiction")) {
                        subjectArea = scanner.nextLine();
                    }

                    for (LibraryItem item : libraryManagementSystem.getItems()) {
                        if (fiction.equalsIgnoreCase("fiction")) {
                            if (item.getTitle().equals(title) &&
                                    item.getYear() == year && ((Book) item).getAuthor().equals(author) &&
                                    item.isAvailable() == available &&
                                    ((Book) item).getIbsn().equals(ibsn) &&
                                    item.getCost() == cost &&
                                    ((FictionBook) item).getGenre().equals(genre)) {
                                libraryManagementSystem.removeBook((Book) item);
                                break;
                            } else throw new IllegalArgumentException("Не существует такой книги в системе");
                        } else if (fiction.equalsIgnoreCase("non fiction")) {
                            if (item.getTitle().equals(title) &&
                                    item.getYear() == year && ((Book) item).getAuthor().equals(author) &&
                                    item.isAvailable() == available &&
                                    ((Book) item).getIbsn().equals(ibsn) &&
                                    item.getCost() == cost &&
                                    ((NonFictionBook) item).getSubjectArea().equals(subjectArea)) {
                                libraryManagementSystem.removeBook((Book) item);
                                break;
                            } else throw new IllegalArgumentException("Не существует такой книги в системе");
                        } else throw new IllegalArgumentException("Неправильно введён тип литературы");
                    }
                }
                case "3" -> {
                    String title = scanner.nextLine();
                    int year = Integer.parseInt(scanner.nextLine());
                    boolean available = Boolean.parseBoolean(scanner.nextLine());
                    String author = scanner.nextLine();
                    String ibsn = scanner.nextLine();
                    double cost = Double.parseDouble(scanner.nextLine());

                    String newTitle = scanner.nextLine();
                    String newAuthor = scanner.nextLine();
                    String newIbsn = scanner.nextLine();

                    for (LibraryItem item : libraryManagementSystem.getItems()) {
                        if (item.getTitle().equals(title) &&
                                item.getYear() == year && ((Book) item).getAuthor().equals(author) &&
                                item.isAvailable() == available &&
                                ((Book) item).getIbsn().equals(ibsn) &&
                                item.getCost() == cost) {
                            libraryManagementSystem.updateBook((Book) item, newTitle, newAuthor, newIbsn);
                            break;
                        } else throw new IllegalArgumentException("Не существует такой книги в системе");
                    }
                }
                case "4" -> {
                    String name = scanner.nextLine();
                    String surname = scanner.nextLine();
                    String readerTicket = scanner.nextLine();
                    String red = scanner.nextLine();

                    if (red.equalsIgnoreCase("student")) {
                        String studentId = scanner.nextLine();
                        Reader student = new Student(name, surname, readerTicket, studentId);
                        libraryManagementSystem.addReader(student);
                    } else if (red.equalsIgnoreCase("professor")) {
                        String department = scanner.nextLine();
                        Reader professor = new Professor(name, surname, readerTicket, department);
                        libraryManagementSystem.addReader(professor);
                    } else throw new IllegalArgumentException("Неправильно введён статус читателя");
                }
                case "5" -> {
                    String name = scanner.nextLine();
                    String surname = scanner.nextLine();
                    String readerTicket = scanner.nextLine();
                    String red = scanner.nextLine();
                    String studentId = "";
                    String department = "";

                    if (red.equalsIgnoreCase("student")) {
                        studentId = scanner.nextLine();
                    } else if (red.equalsIgnoreCase("professor")) {
                        department = scanner.nextLine();
                    }

                    for (Reader reader1 : libraryManagementSystem.getReaders()) {
                        if (red.equalsIgnoreCase("student")) {
                            if (reader1.getName().equals(name) &&
                                    reader1.getSurname().equals(surname) &&
                                    reader1.getReaderTicket().equals(readerTicket) &&
                                    ((Student) reader1).getStudentId().equals(studentId)) {
                                libraryManagementSystem.removeReader(reader1);
                                break;
                            } else throw new IllegalArgumentException("Не существует такого читателя в системе");
                        } else if (red.equalsIgnoreCase("professor")) {
                            if (reader1.getName().equals(name) &&
                                    reader1.getSurname().equals(surname) &&
                                    reader1.getReaderTicket().equals(readerTicket) &&
                                    ((Professor) reader1).getDepartment().equals(department)) {
                                libraryManagementSystem.removeReader(reader1);
                                break;
                            } else throw new IllegalArgumentException("Не существует такого читателя в системе");
                        } else throw new IllegalArgumentException("Неправильно введён статус читателя");
                    }
                }
                case "6" -> {
                    String name = scanner.nextLine();
                    String surname = scanner.nextLine();
                    String readerTicket = scanner.nextLine();

                    String newName = scanner.nextLine();
                    String newSurname = scanner.nextLine();
                    String newReaderTicket = scanner.nextLine();

                    for (Reader reader1 : libraryManagementSystem.getReaders()) {
                        if (reader1.getName().equals(name) &&
                                reader1.getSurname().equals(surname) &&
                                reader1.getReaderTicket().equals(readerTicket)) {
                            libraryManagementSystem.updateReader(reader1, newName, newSurname, newReaderTicket);
                            break;
                        } else throw new IllegalArgumentException("Не существует такого читателя в системе");
                    }
                }
                case "7" -> {
//                    System.out.println("Введите имя читателя:");
                    String name = scanner.nextLine();
//                    System.out.println("Введите фамилию читателя:");
                    String surname = scanner.nextLine();
//                    System.out.println("Введите номер читательского билета:");
                    String readerTicket = scanner.nextLine();
//                    System.out.println("Введите статус читателя:");
                    String red = scanner.nextLine();

//                    System.out.println("Введите название книги:");
                    String title = scanner.nextLine();
//                    System.out.println("Введите год издания книги:");
                    int year = Integer.parseInt(scanner.nextLine());
//                    System.out.println("Введите доступность книги:");
                    boolean available = Boolean.parseBoolean(scanner.nextLine());
//                    System.out.println("Введите автора книги:");
                    String author = scanner.nextLine();
//                    System.out.println("Введите ibsn книги:");
                    String ibsn = scanner.nextLine();
//                    System.out.println("Введите стоимость книги:");
                    double cost = Double.parseDouble(scanner.nextLine());
//                    System.out.println("Введите fiction книги:");
                    String fiction = scanner.nextLine();
                    Reader reader1 = null;
                    Book book1 = null;
                    String studentId = "";
                    String department = "";

                    if (red.equalsIgnoreCase("student")) {
//                            System.out.println("Введите studentId читателя:");
                        studentId = scanner.nextLine();
                    } else if (red.equalsIgnoreCase("professor")) {
//                            System.out.println("Введите department читателя:");
                        department = scanner.nextLine();
                    }

                    for (Reader reader2 : libraryManagementSystem.getReaders()) {
                        if (red.equalsIgnoreCase("student")) {
                            if (reader2.getName().equals(name) &&
                                    reader2.getSurname().equals(surname) &&
                                    reader2.getReaderTicket().equals(readerTicket) &&
                                    ((Student) reader2).getStudentId().equals(studentId)) {
                                reader1 = reader2;
                            }
                        } else if (red.equalsIgnoreCase("professor")) {
                            if (reader2.getName().equals(name) &&
                                    reader2.getSurname().equals(surname) &&
                                    reader2.getReaderTicket().equals(readerTicket) &&
                                    ((Professor) reader2).getDepartment().equals(department)) {
                                reader1 = reader2;
                            }
                        }
                    }
                    String genre = "";
                    String subjectArea = "";
                    if (fiction.equalsIgnoreCase("fiction")) {
//                        System.out.println("Введите genre книги:");
                        genre = scanner.nextLine();
                    } else if (fiction.equalsIgnoreCase("non fiction")) {
//                        System.out.println("Введите subjectArea книги:");
                        subjectArea = scanner.nextLine();
                    }

                    for (LibraryItem item : libraryManagementSystem.getItems()) {
                        if (fiction.equalsIgnoreCase("fiction")) {
                            if (item.getTitle().equals(title) &&
                                    item.getYear() == year && item.isAvailable() == available &&
                                    ((Book) item).getAuthor().equals(author) &&
                                    ((Book) item).getIbsn().equals(ibsn) &&
                                    item.getCost() == cost &&
                                    ((FictionBook) item).getGenre().equals(genre)) {
                                book1 = (Book) item;
                            }
                        } else if (fiction.equalsIgnoreCase("non fiction")) {
                            if (item.getTitle().equals(title) &&
                                    item.getYear() == year && item.isAvailable() == available &&
                                    ((Book) item).getAuthor().equals(author) &&
                                    ((Book) item).getIbsn().equals(ibsn) &&
                                    item.getCost() == cost &&
                                    ((NonFictionBook) item).getSubjectArea().equals(subjectArea)) {
                                book1 = (Book) item;
                            }
                        }
                    }
                    libraryManagementSystem.borrowItem(reader1, book1);
                }
                case "8" -> {
                    String name = scanner.nextLine();
                    String surname = scanner.nextLine();
                    String readerTicket = scanner.nextLine();
                    String red = scanner.nextLine();

                    String title = scanner.nextLine();
                    int year = Integer.parseInt(scanner.nextLine());
                    boolean available = Boolean.parseBoolean(scanner.nextLine());
                    String author = scanner.nextLine();
                    String ibsn = scanner.nextLine();
                    double cost = Double.parseDouble(scanner.nextLine());
                    String fiction = scanner.nextLine();
                    Reader reader1 = null;
                    Book book1 = null;
                    String studentId = "";
                    String department = "";

                    if (red.equalsIgnoreCase("student")) {
                        studentId = scanner.nextLine();
                    } else if (red.equalsIgnoreCase("professor")) {
                        department = scanner.nextLine();
                    }

                    for (Reader reader2 : libraryManagementSystem.getReaders()) {
                        if (red.equalsIgnoreCase("student")) {
                            if (reader2.getName().equals(name) &&
                                    reader2.getSurname().equals(surname) &&
                                    reader2.getReaderTicket().equals(readerTicket) &&
                                    ((Student) reader2).getStudentId().equals(studentId)) {
                                reader1 = reader2;
                            }
                        } else if (red.equalsIgnoreCase("professor")) {
                            if (reader2.getName().equals(name) &&
                                    reader2.getSurname().equals(surname) &&
                                    reader2.getReaderTicket().equals(readerTicket) &&
                                    ((Professor) reader2).getDepartment().equals(department)) {
                                reader1 = reader2;
                            }
                        }
                    }

                    assert reader1 != null;
                    String genre = "";
                    String subject = "";

                    if (fiction.equalsIgnoreCase("fiction")) {
                        genre = scanner.nextLine();
                    } else if (fiction.equalsIgnoreCase("non fiction")) {
                        subject = scanner.nextLine();
                    }

                    for (LibraryItem item : reader1.getBorrowedItems()) {
                        if (fiction.equalsIgnoreCase("fiction")) {
                            if (item.getTitle().equals(title) &&
                                    item.getYear() == year && item.isAvailable() == available &&
                                    ((Book) item).getAuthor().equals(author) &&
                                    ((Book) item).getIbsn().equals(ibsn) &&
                                    item.getCost() == cost &&
                                    ((FictionBook) item).getGenre().equals(genre)) {
                                book1 = (Book) item;
                            }
                        } else if (fiction.equalsIgnoreCase("non fiction")) {
                            if (item.getTitle().equals(title) &&
                                    item.getYear() == year && item.isAvailable() == available &&
                                    ((Book) item).getAuthor().equals(author) &&
                                    ((Book) item).getIbsn().equals(ibsn) &&
                                    item.getCost() == cost &&
                                    ((NonFictionBook) item).getSubjectArea().equals(subject)) {
                                book1 = (Book) item;
                            }
                        }
                    }
                    libraryManagementSystem.returnItem(reader1, book1);
                    assert book1 != null;
                    double fine = book1.calculateFineForDays(book1.getBorrowDate(), book1.getReturnDate());
                    System.out.println();
                    System.out.println("fine = " + fine);
                }
                case "9" -> {
                    String author = scanner.nextLine();

                    System.out.println(libraryManagementSystem.searchLibraryItems(author));
                }
                case "10" -> {
                    String surname = scanner.nextLine();

                    System.out.println(libraryManagementSystem.searchReader(surname));
                }
                default -> throw new IllegalArgumentException("Введён неверный номер пункта");
            }
            System.out.println();
            System.out.println(libraryManagementSystem);
            System.out.println();
            System.out.println("""
                Введите номер пункта для реализации функции:
                1. addBook
                2. removeBook
                3. updateBook
                4. addReader
                5. removeReader
                6. updateReader
                7. borrowItem
                8. returnItem
                9. searchLibraryItems
                10. searchReader""");
            command = scanner.nextLine();
        }
    }
}

/*
1
popa
1900
true
popkin
1
900
1
fiction
roman
 */

/*
1
master
1976
true
bulgakov
2
1000
2
fiction
novel
 */

/*
4
lora
yu
1
student
1
 */

/*
7
lora
yu
1
student
master
1976
true
bulgakov
2
1000
fiction
1
novel
 */

/*
4
popa
popkin
1
professor
1
 */

/*
7
lora
yu
1
student
popa
1900
true
popkin
1
900
fiction
1
roman
 */

/*
8
lora
yu
1
student
popa
1900
false
popkin
1
900
fiction
1
roman
 */


/*
1
popa
1900
true
popkin
1
900
1
fiction
novel
 */

/*
1
bird
1905
true
popkin
2
1000
2
non fiction
scientific
 */

/*
1
doll
2000
false
twen
3
1200
3
fiction
 */