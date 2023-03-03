package libraryModels;
import java.util.Date;
import java.util.List;

import libraryModels.Book;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Author{
    @Id
    private Long id;
    @NotNull
    private String au_name;
    @NotNull
    private String telephone;
    @NotNull
    private String email;
    @NotNull
    private String rating;
//    @NotNull
//    @Size(min=1, message="Вы должны указать хотя бы одну книгу")
//    private List<Book> books;


}


