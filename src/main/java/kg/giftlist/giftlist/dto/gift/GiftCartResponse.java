package kg.giftlist.giftlist.dto.gift;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.giftlist.giftlist.db.models.SubCategory;
import kg.giftlist.giftlist.dto.mapper.complaint.ComplaintGiftResponse;
import kg.giftlist.giftlist.enums.Status;
import kg.giftlist.giftlist.db.models.Booking;
import kg.giftlist.giftlist.db.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GiftCartResponse {

    private Long giftId;
    private String name;
    private String photo;
    private Status status;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate createdAt;

    private String description;
    private Category category;
    private SubCategory subCategory;
    private Boolean isBlock;
    private List<ComplaintGiftResponse> complaints;
    private Booking booking;

}
