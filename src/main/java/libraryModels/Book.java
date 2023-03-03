package libraryModels;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table
public class Book {
    @Id
//    @NotNull
    private final Long id;
    @NotEmpty(message = "Вы не заполнили название книги")
    private final String book_name;
    @Size(min=3)
    @NotEmpty(message = "Вы не заполнили переплет книги")
    private final String binding;

    @NotEmpty(message = "Вы не заполнили издателя книги")
    private final String publisher;
    @NotEmpty(message = "Вы не заполнили год издания книги")
    private final String publishedYear;
    @NotEmpty(message = "Вы не заполнили жанр книги")
    private final String genre;
    @NotNull(message = "Вы не выбрали автора книги")
    private final Integer authorId;

}
