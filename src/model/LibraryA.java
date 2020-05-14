package model;

import controller.LibrariesController;
import controller.MyDate;
import enums.AddBook;
import enums.Libraries;
import enums.Type;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LibraryA implements Library {

    private static LibraryA instance;

    private List<Employee> employees;
    private Map<Book, Integer> books;
    private Map<Book, Integer> borrowedBooks;
    private int numbersOfBooks = 0;
    private int numbersOfEmployee = 0;

    public static LibraryA getInstance() {
        if(instance == null) {
            instance = new LibraryA();
        }
        return instance;
    }

    private LibraryA() {
        employees = new ArrayList<>();
        books = new HashMap<>();
        borrowedBooks = new HashMap<>();
    }

    @Override
    public Book search(Book book) {
        for(Book test : books.keySet()) {
            if(test.getBookName().equalsIgnoreCase(book.getBookName())) {
                if(test.getPublishedYear() == book.getPublishedYear()) {
                    if(test.getTranslatorName().equalsIgnoreCase(book.getTranslatorName())) {
                        return test;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public AddBook addBook(Book book) {
        if (numbersOfBooks == MAX_BOOKS) {
            return AddBook.LIBRARY_IS_FULL;
        }
        Book test = CentralManagement.searchBookInAllBooks(book);
        if(test == null) {
            return AddBook.BOOK_NEVER_EXIST;//This book dose not ever exist!
        }else if (test.getBookPlace() == Libraries.NO_WHERE_YET) {
            books.put(test, 1);
            CentralManagement.allBooksInLibraries.put(test, 1);
            numbersOfBooks++;
            test.setBookPlace(Libraries.LIBRARY_A);
            return AddBook.NEW_ADDED_SUCCESSFULLY;//New book has successfully added to this library.
        } else if (test.getBookPlace() == Libraries.LIBRARY_A) {
            if (test.getNumbersAvailable() - books.get(test) == 0) {
                return AddBook.NO_OTHER_BOOK_TO_ADD;
            }
            books.replace(test, books.get(test)+1);
            CentralManagement.allBooksInLibraries.replace(test, books.get(test)+1);
            numbersOfBooks++;
            return AddBook.ADDED_SUCCESSFULLY;//The book has successfully added to this library.
        } else {
            return AddBook.BELONG_TO_ANOTHER_LIBRARY;//This book belongs to the another library!
        }
    }

    @Override
    public boolean borrowBook(Book book) {
        Book test = search(book);
        if (test == null || books.get(test) == 0) {
            return false;//the book does not exist!
        }else {
            books.replace(test, books.get(test)-1);
            CentralManagement.allBooksInLibraries.replace(test, books.get(test)-1);
            numbersOfBooks--;
            if (borrowedBooks.get(test) == null)
                borrowedBooks.put(test, 1);
            else
                borrowedBooks.replace(test, borrowedBooks.get(test)+1);
            return true;
        }
    }

    @Override
    public void returnBook(Book book) {
        books.replace(book, books.get(book)+1);
        CentralManagement.allBooksInLibraries.replace(book, books.get(book)+1);
        borrowedBooks.replace(book, borrowedBooks.get(book)-1);
        if (borrowedBooks.get(book) == 0) {
            borrowedBooks.remove(book);
        }
        numbersOfBooks++;
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
        numbersOfEmployee++;
        CentralManagement.allActiveEmployees.add(employee);
    }

    @Override
    public void changeEmployeeSchedule(long nationalCode, List<WeekDays> newSchedule) {
        for (Employee employee : employees) {
            if (employee.nationalCode == nationalCode) {
                employee.updateWorkingDays(newSchedule);
                break;
            }
        }
    }

    @Override
    public void setFineForDelay(MyDate currentDay, int addDay) {
        for (Book book : borrowedBooks.keySet()) {
            for (Person person : book.getBorrowers().keySet()) {
                if (LibrariesController.getInstance().datePass(currentDay, book.getBorrowers().get(person))) {
                    int dayPassed;
                    dayPassed = LibrariesController.getInstance().daysPassed(currentDay, book.getBorrowers().get(person));
                    long fine = addDay > dayPassed ? dayPassed * FINE : addDay * FINE;
                    if (person.getType() == Type.STUDENT) {
                        Student student = (Student)person;
                        student.fine(fine);
                    } else if (person.getType() == Type.PROFESSOR) {
                        Professor professor = (Professor)person;
                        professor.fine(fine);
                    }
                }
            }
        }
    }

    public int getNumbersOfBooks() {
        return numbersOfBooks;
    }

    public int getNumbersOfEmployee() {
        return numbersOfEmployee;
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public Map<Book, Integer> getBorrowedBooks() {
        return borrowedBooks;
    }
}
