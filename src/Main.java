import controller.BookController;
import repository.DataBookRepository;
import repository.FileBookRepository;
import repository.InMemoryBookRepository;
import repository.SqlBookRepository;
import model.Book;
import service.BookService;
import utility.DBUtil;
import view.MainView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        DBUtil db = new DBUtil();
        SqlBookRepository sqlRepo = new SqlBookRepository(db);
        InMemoryBookRepository memoryRepo = new InMemoryBookRepository(bookList);
        FileBookRepository fileRepo = new FileBookRepository();
        DataBookRepository dataRepo = new DataBookRepository(sqlRepo, memoryRepo, fileRepo);
        MainView view = new MainView();

        BookService service = new BookService(dataRepo, memoryRepo);

        BookController controller = new BookController(service, view);

        controller.start();



    }
}