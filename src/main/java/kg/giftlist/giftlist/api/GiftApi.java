package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gifts")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Gift API", description = "User with role \"User, Admin\"  can create, update or delete gifts")
public class GiftApi {

    private final GiftServiceImpl giftService;

    @Operation(summary = "Create gift", description = "The user can create a gift")
    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping
    public GiftResponse create(@RequestBody GiftRequest giftRequest) {
        return giftService.create(giftRequest);
    }

    @Operation(summary = "Update gift", description = "The user can update a gift ")
    @PreAuthorize("hasAnyAuthority('USER')")
    @PutMapping("{giftId}")
    public GiftResponse update(@PathVariable Long giftId, @RequestBody GiftRequest request) {
        return giftService.update(giftId, request);
    }

    @Operation(summary = "Delete gift", description = "User can delete gift by id")
    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping("{giftId}")
    public SimpleResponse deleteById(@PathVariable Long giftId) {
        return giftService.deleteById(giftId);
    }

    @Operation(summary = "Find gift by id", description = "User can find gift")
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("{giftId}")
    public GiftResponse findById(@PathVariable Long giftId) {
        return giftService.getGiftById(giftId);
    }

    @Operation(summary = "Get all own gifts", description = "User can get only own all gifts")
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("my-gifts")
    public List<GiftResponse> getAllOwnGifts() {
        return giftService.getAllOwnGifts();
    }

    @Operation(summary = "Get all gifts", description = "User can get all gifts")
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    public List<GiftResponse> getAllGifts() {
        return giftService.getAllGiftsForUser();
    }

    @Operation(summary = "Search gifts by filter", description = "User can search gifts by filter")
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("filter")
    public List<GiftResponse> filter(
            @RequestParam(required = false, defaultValue = "all") String search,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long subCategoryId) {
        return giftService.filter(search, status, categoryId, subCategoryId);
    }

}
