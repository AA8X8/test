package library;

import library.items.Book;
import library.items.LibraryItem;
import library.persons.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryManagementSystem {

    private final List<LibraryItem> items;
    private final List<Reader> readers;
    private final HashMap<LibraryItem, Integer> availableCopies;

    public LibraryManagementSystem() {
        this.items = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.availableCopies = new HashMap<>();
    }

    public void addBook(Book book, int position) {  // copies
        if (book == null || position <= 0) {
            throw new IllegalArgumentException("Не передана книга или " +
                    "неправильно введена позиция для добавления в систему");
        }
//        if (!availableCopies.containsKey(book)) {
//            if (items.size() < position) {
//                items.add(book);
//            } else items.add(position, book);
//        }
//        int countCopies = availableCopies.computeIfAbsent(book, k -> 0) + 1;
//        availableCopies.put(book, countCopies);

        items.add(book);
        availableCopies.put(book, position);
    }

    public void removeBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Не передана книга для удаления из системы");
        }

        int copies = availableCopies.get(book);
        if (copies > 1) {
            copies--;
            availableCopies.put(book, copies);
        } else {
            items.remove(book);
            availableCopies.remove(book);
        }
    }

    public void updateBook(Book book, String title, String author, String ibsn) {
        if (book == null) {
            throw new IllegalArgumentException("Не передана книга для обновления данных");
        }
        if (title.isEmpty() || author.isEmpty() || ibsn.isEmpty()) {
            throw new IllegalArgumentException("Не переданы необходимые данные для обновления " +
                    "информации о книге");
        }

        if (items.contains(book)) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setIbsn(ibsn);
        } else throw new IllegalArgumentException("Нет такой книги в системе");
    }

    public void addReader(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Не передан читатель для добавления в систему");
        }
        readers.add(reader);
    }

    public void removeReader(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Не передан читатель для удаления из системы");
        }
        readers.remove(reader);
    }

    public void updateReader(Reader reader, String name, String surname, String readerTicket) {
        if (reader == null) {
            throw new IllegalArgumentException("Не передан читатель для обновления данных");
        }
        if (name.isEmpty() || surname.isEmpty() || readerTicket.isEmpty()) {
            throw new IllegalArgumentException("Не переданы необходимые данные для обновления " +
                    "информации о читателе");
        }

        if (readers.contains(reader)) {
            reader.setName(name);
            reader.setSurname(surname);
            reader.setReaderTicket(readerTicket);
        } else throw new IllegalArgumentException("Нет такого читателя в системе");
    }

    public boolean borrowItem(Reader reader, LibraryItem item) {
        if (reader == null) {
            throw new IllegalArgumentException("Не передан читатель для взятия книги");
        }
        if (item == null) {
            throw new IllegalArgumentException("Не передана книга, которую читатель хочет взять");
        }
        if (items.contains(item)) {
            int copies = availableCopies.get(item);
            if (copies > 0) {
                copies--;
                availableCopies.put(item, copies);
                reader.borrowItem(item);
                item.borrowItem(reader);
                return true;
            }
        } else throw new IllegalArgumentException("Нет такой книги в библиотеке");
        return false;
    }

    public void returnItem(Reader reader, LibraryItem item) {
        if (reader == null) {
            throw new IllegalArgumentException("Не передан читатель для возврата книги");
        }
        if (item == null) {
            throw new IllegalArgumentException("Не передана книга, которую читатель хочет вернуть");
        }
        if (reader.getBorrowedItems().contains(item) &&
                items.contains(item)) {
            int copies = availableCopies.get(item);
            copies++;
            availableCopies.put(item, copies);
            reader.returnItem(item);
            item.returnItem();
        } else throw new IllegalArgumentException("Читатель не брал такую книгу/ " +
                "Такой книги нет в системе");
    }

    public List<LibraryItem> searchLibraryItems(String author) {
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Не передан автор для поиска книг");
        }
        List<LibraryItem> libraryItems = new ArrayList<>();

        for (LibraryItem item : items) {
            if (((Book) item).getAuthor().equalsIgnoreCase(author) &&
                    ((Book) item).matches(((Book) item).getAuthor())) {
                libraryItems.add(item);
            }
        }
        return libraryItems;
    }

    public List<Reader> searchReader(String surname) {
        if (surname.isEmpty()) {
            throw new IllegalArgumentException("Не передана фамилия для поиска читателей");
        }
        List<Reader> libraryReaders = new ArrayList<>();

        for (Reader reader : readers) {
            if (reader.getSurname().equalsIgnoreCase(surname) &&
                    reader.matches(reader.getSurname())) {
                libraryReaders.add(reader);
            }
        }
        return libraryReaders;
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    @Override
    public String toString() {
        return "LibraryManagementSystem{" +
                "items=" + items +
                ", readers=" + readers +
                ", availableCopes=" + availableCopies +
                '}';
    }
}
