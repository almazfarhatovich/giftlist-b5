package kg.giftlist.giftlist.db.service.impl;

import kg.giftlist.giftlist.db.models.*;
import kg.giftlist.giftlist.db.repositories.*;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.gift.mapper.GiftViewMapper;
import kg.giftlist.giftlist.dto.mapper.wish.WishViewMapper;
import kg.giftlist.giftlist.dto.wish.WishResponse;
import kg.giftlist.giftlist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.ForbiddenException;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ComplaintServiceImpl {

    private final WishRepository wishRepository;
    private final GiftRepository giftRepository;
    private final UserRepository userRepo;
    private final ComplaintRepository complaintRepository;
    private final WishViewMapper wishViewMapper;
    private final GiftViewMapper giftViewMapper;

    @Transactional
    public SimpleResponse sendComplaintToWish(Long wishId, String text) {
        User user = getAuthenticatedUser();
        Complaint complaint = new Complaint();
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Wish with id: " + wishId + "not found"));
        complaint.setText(text);
        complaint.setWishes(wish);
        complaint.setFromUser(user);
        complaintRepository.save(complaint);
        log.info("Complaint to wish with id: {} successfully send", wish.getId());
        return new SimpleResponse("Отправлено! Спасибо, что сообщили нам об этом", "Ваши отзывы помогают нам сделать сообщество GIFT LIST безопасной средой для всех");

    }

    @Transactional
    public SimpleResponse sendComplaintToGift(Long giftId, String text) {
        User user = getAuthenticatedUser();
        Complaint complaint = new Complaint();
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Gift with id: " + giftId + "not found"));
        complaint.setText(text);
        complaint.setGift(gift);
        complaint.setFromUser(user);
        complaintRepository.save(complaint);
        log.info("Complaint to gift with id: {} successfully send", gift.getId());
        return new SimpleResponse("Отправлено!Спасибо, что сообщили нам об этом", "Ваши отзывы помогают нам сделать сообщество GIFT LIST безопасной средой для всех");

    }

    public SimpleResponse deleteComplaintById(Long id) {
        boolean exists = complaintRepository.existsById(id);
        if (!exists) {
            log.error("Complaint with id = " + id + " not found!");
            throw new NotFoundException("Complaint with id = " + id + " not found!");
        }
        complaintRepository.deleteById(id);
        log.info("Complaint to with id: {} successfully send", id);
        return new SimpleResponse("Deleted!", "Complaint successfully deleted!");
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepo.findByEmail(login).orElseThrow(() ->
                new ForbiddenException("User not found!"));
    }

    public List<WishResponse> getAllComplaintWishes() {
        return wishViewMapper.getAllWishes(wishRepository.getAllComplaintsWishes());
    }

    public List<GiftResponse> getAllComplaintGifts() {
        return giftViewMapper.getAllGifts(giftRepository.getAllComplaintGifts());
    }

    public WishResponse getComplaintWishById(Long wishId) {
        Wish wish = wishRepository.findById(wishId).orElseThrow(() ->
                new NotFoundException("Wish with id " + wishId + " not found"));
        User user = wish.getUser();
        return wishViewMapper.viewCommonWishCard(user,wish);
    }

    public GiftResponse getComplaintGiftById(Long giftId) {
        Gift gift = giftRepository.findById(giftId).orElseThrow(() ->
                new NotFoundException("Wish with id " + giftId + " not found"));
        User user = gift.getUser();
        return giftViewMapper.viewCommonGiftCard(user,gift);
    }
}
