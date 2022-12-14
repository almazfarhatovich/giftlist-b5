package kg.giftlist.giftlist.dto.holiday;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HolidayResponse {

    private Long id;
    private String name;
    private String photo;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate holidayDate;

}
