package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.ComplaintServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/complaints")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Complaint API", description = "User with role \"User\" can send complaints. Only Admin can see all complaints")
public class ComplaintApi {

    private final ComplaintServiceImpl complaintService;

    @Operation(summary = "Send complaint to wish", description = "User can send complain to Admin")
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("wish/{wishId}")
    public SimpleResponse sendComplaintToWish(@PathVariable Long wishId, @RequestParam String text) {
        return complaintService.sendComplaintToWish(wishId, text);
    }

    @Operation(summary = "Send complaint to gift", description = "User can send complaint to Admin")
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("gift/{giftId}")
    public SimpleResponse sendComplaintToGift(@PathVariable Long giftId, @RequestParam String text) {
        return complaintService.sendComplaintToGift(giftId, text);
    }

    @Operation(summary = "Delete complaint by id", description = "Only Admin can delete complaint")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{complaintId}")
    public SimpleResponse deleteComplaintById(@PathVariable Long complaintId) {
        return complaintService.deleteComplaintById(complaintId);
    }

    @Operation(summary = "All complaint wishes", description = "Admin can see all complaint wishes")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("wishes")
    public List<WishResponse> getAllComplaintWishes() {
        return complaintService.getAllComplaintWishes();
    }

    @Operation(summary = "All complaint gifts", description = "Admin can see all complaint gifts")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("gifts")
    public List<GiftResponse> getAllComplaintGifts() {
        return complaintService.getAllComplaintGifts();
    }

    @Operation(summary = "Get complaint wish by id", description = "Admin can get wish by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("wish/{wishId}")
    public WishResponse getComplaintWishById(@PathVariable Long wishId) {
        return complaintService.getComplaintWishById(wishId);
    }

    @Operation(summary = "Get complaint gift by id", description = "Admin can get gift by id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("gift/{giftId}")
    public GiftResponse getComplaintGiftById(@PathVariable Long giftId) {
        return complaintService.getComplaintGiftById(giftId);
    }

}
