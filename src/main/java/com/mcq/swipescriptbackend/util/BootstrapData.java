package com.mcq.swipescriptbackend.util;

import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.entity.Photo;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

        @Value("${app.image.url-prefix}")
        private String imageUrlPrefix;

    @Override
    public void run(String... args) {

        List<AppUser> testUsers = List.of(
                createAppUser(
                        "barkley_paws",
                        "barkbark123",
                        LocalDate.of(1988, 3, 15),
                        "Barkley",
                        "male",
                        List.of(createPhoto("barkley.png", true)),
                        "Loyal, adventurous, and a stick-fetching expert, I’m your go-to for spontaneous park adventures and backyard excavations. Warning: I may wag too hard when happy.",
                        "Digging up your prized garden, stealing shoes, and barking at the mysterious mailman who dares approach the house.",
                        "A fellow adventurer who doesn’t mind mud and shares my love for chasing squirrels. Bonus points if you can throw a ball farther than my last human.",
                        "Dogtown",
                        "Barkland"
                ),
                createAppUser(
                        "bella_barker",
                        "fluffydog456",
                        LocalDate.of(2000, 8, 12),
                        "Bella",
                        "female",
                        List.of(createPhoto("bella.png", true)),
                        "Charming and full of life, I’m the life of every puppy party and the queen of the couch. I’m all about making friends and striking adorable poses.",
                        "Tug-of-war, gourmet kibble, and wearing my collection of seasonal bandanas. Also a big fan of belly rubs (minimum 5 per day required).",
                        "Someone who loves to play dress-up and doesn’t mind being photobombed during selfies.",
                        "Fur City",
                        "Tailtopia"
                ),
                createAppUser(
                        "cocoa_the_chill",
                        "lazybones123",
                        LocalDate.of(2002, 6, 5),
                        "Cocoa",
                        "female",
                        List.of(createPhoto("cocoa.png", true)),
                        "The chillest dog you’ll ever meet. My motto? ‘Work hard, nap harder.’ I specialize in long naps, slow walks, and the occasional burst of zoomies when the mood strikes.",
                        "Cuddling, lounging in sunbeams, and pretending not to hear you when I’m comfortable on the couch. Oh, and belly rubs—lots of them.",
                        "A fellow couch potato who doesn’t mind sharing their snacks and loves a good movie marathon.",
                        "Sleepy Hollow",
                        "Chilltopia"
                ),
                createAppUser(
                        "carol",
                        "pass",
                        LocalDate.of(1975, 4, 25),
                        "Carol",
                        "female",
                        List.of(
                                createPhoto("carol1.png", true),
                                createPhoto("carol2.png", false)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "missy",
                        "password",
                        LocalDate.of(2006, 4, 25),
                        "Missy",
                        "female",
                        List.of(
                                createPhoto("nice5.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "fratdog",
                        "password",
                        LocalDate.of(2004, 4, 25),
                        "Keith",
                        "male",
                        List.of(
                                createPhoto("fratdog.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "woofman",
                        "password",
                        LocalDate.of(1999, 4, 25),
                        "Ian",
                        "male",
                        List.of(
                                createPhoto("woofman.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "divadiva",
                        "password",
                        LocalDate.of(1996, 4, 25),
                        "Diva",
                        "female",
                        List.of(
                                createPhoto("divadiva.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "doofy",
                        "password",
                        LocalDate.of(2003, 4, 25),
                        "Doofy",
                        "male",
                        List.of(
                                createPhoto("doofy.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "barkley_paws1",
                        "barkbark123",
                        LocalDate.of(1988, 3, 15),
                        "Barkley",
                        "male",
                        List.of(createPhoto("barkley.png", true)),
                        "Loyal, adventurous, and a stick-fetching expert, I’m your go-to for spontaneous park adventures and backyard excavations. Warning: I may wag too hard when happy.",
                        "Digging up your prized garden, stealing shoes, and barking at the mysterious mailman who dares approach the house.",
                        "A fellow adventurer who doesn’t mind mud and shares my love for chasing squirrels. Bonus points if you can throw a ball farther than my last human.",
                        "Dogtown",
                        "Barkland"
                ),
                createAppUser(
                        "bella_barker1",
                        "fluffydog456",
                        LocalDate.of(2000, 8, 12),
                        "Bella",
                        "female",
                        List.of(createPhoto("bella.png", true)),
                        "Charming and full of life, I’m the life of every puppy party and the queen of the couch. I’m all about making friends and striking adorable poses.",
                        "Tug-of-war, gourmet kibble, and wearing my collection of seasonal bandanas. Also a big fan of belly rubs (minimum 5 per day required).",
                        "Someone who loves to play dress-up and doesn’t mind being photobombed during selfies.",
                        "Fur City",
                        "Tailtopia"
                ),
                createAppUser(
                        "cocoa_the_chill1",
                        "lazybones123",
                        LocalDate.of(2002, 6, 5),
                        "Cocoa",
                        "female",
                        List.of(createPhoto("cocoa.png", true)),
                        "The chillest dog you’ll ever meet. My motto? ‘Work hard, nap harder.’ I specialize in long naps, slow walks, and the occasional burst of zoomies when the mood strikes.",
                        "Cuddling, lounging in sunbeams, and pretending not to hear you when I’m comfortable on the couch. Oh, and belly rubs—lots of them.",
                        "A fellow couch potato who doesn’t mind sharing their snacks and loves a good movie marathon.",
                        "Sleepy Hollow",
                        "Chilltopia"
                ),
                createAppUser(
                        "carol1",
                        "pass",
                        LocalDate.of(1975, 4, 25),
                        "Carol",
                        "female",
                        List.of(
                                createPhoto("carol1.png", true),
                                createPhoto("carol2.png", false)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "missy1",
                        "password",
                        LocalDate.of(2006, 4, 25),
                        "Missy",
                        "female",
                        List.of(
                                createPhoto("nice5.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "fratdog1",
                        "password",
                        LocalDate.of(2004, 4, 25),
                        "Keith",
                        "male",
                        List.of(
                                createPhoto("fratdog.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "woofman1",
                        "password",
                        LocalDate.of(1999, 4, 25),
                        "Ian",
                        "male",
                        List.of(
                                createPhoto("woofman.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "divadiva1",
                        "password",
                        LocalDate.of(1996, 4, 25),
                        "Diva",
                        "female",
                        List.of(
                                createPhoto("divadiva.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "doofy1",
                        "password",
                        LocalDate.of(2003, 4, 25),
                        "Doofy",
                        "male",
                        List.of(
                                createPhoto("doofy.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville1",
                        "Snoutopia"
                ),
                createAppUser(
                        "barkley_paws2",
                        "barkbark123",
                        LocalDate.of(1988, 3, 15),
                        "Barkley",
                        "male",
                        List.of(createPhoto("barkley.png", true)),
                        "Loyal, adventurous, and a stick-fetching expert, I’m your go-to for spontaneous park adventures and backyard excavations. Warning: I may wag too hard when happy.",
                        "Digging up your prized garden, stealing shoes, and barking at the mysterious mailman who dares approach the house.",
                        "A fellow adventurer who doesn’t mind mud and shares my love for chasing squirrels. Bonus points if you can throw a ball farther than my last human.",
                        "Dogtown",
                        "Barkland"
                ),
                createAppUser(
                        "bella_barker2",
                        "fluffydog456",
                        LocalDate.of(2000, 8, 12),
                        "Bella",
                        "female",
                        List.of(createPhoto("bella.png", true)),
                        "Charming and full of life, I’m the life of every puppy party and the queen of the couch. I’m all about making friends and striking adorable poses.",
                        "Tug-of-war, gourmet kibble, and wearing my collection of seasonal bandanas. Also a big fan of belly rubs (minimum 5 per day required).",
                        "Someone who loves to play dress-up and doesn’t mind being photobombed during selfies.",
                        "Fur City",
                        "Tailtopia"
                ),
                createAppUser(
                        "cocoa_the_chill2",
                        "lazybones123",
                        LocalDate.of(2002, 6, 5),
                        "Cocoa",
                        "female",
                        List.of(createPhoto("cocoa.png", true)),
                        "The chillest dog you’ll ever meet. My motto? ‘Work hard, nap harder.’ I specialize in long naps, slow walks, and the occasional burst of zoomies when the mood strikes.",
                        "Cuddling, lounging in sunbeams, and pretending not to hear you when I’m comfortable on the couch. Oh, and belly rubs—lots of them.",
                        "A fellow couch potato who doesn’t mind sharing their snacks and loves a good movie marathon.",
                        "Sleepy Hollow",
                        "Chilltopia"
                ),
                createAppUser(
                        "carol2",
                        "pass",
                        LocalDate.of(1975, 4, 25),
                        "Carol",
                        "female",
                        List.of(
                                createPhoto("carol1.png", true),
                                createPhoto("carol2.png", false)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "miss2",
                        "password",
                        LocalDate.of(2006, 4, 25),
                        "Missy",
                        "female",
                        List.of(
                                createPhoto("nice5.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "fratdo2",
                        "password",
                        LocalDate.of(2004, 4, 25),
                        "Keith",
                        "male",
                        List.of(
                                createPhoto("fratdog.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "woofman2",
                        "password",
                        LocalDate.of(1999, 4, 25),
                        "Ian",
                        "male",
                        List.of(
                                createPhoto("woofman.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "divadiva2",
                        "password",
                        LocalDate.of(1996, 4, 25),
                        "Diva",
                        "female",
                        List.of(
                                createPhoto("divadiva.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "doofy2",
                        "password",
                        LocalDate.of(2003, 4, 25),
                        "Doofy",
                        "male",
                        List.of(
                                createPhoto("doofy.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "carol4",
                        "pass",
                        LocalDate.of(1975, 4, 25),
                        "Carol",
                        "female",
                        List.of(
                                createPhoto("carol1.png", true),
                                createPhoto("carol2.png", false)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "miss4",
                        "password",
                        LocalDate.of(2006, 4, 25),
                        "Missy",
                        "female",
                        List.of(
                                createPhoto("nice5.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "fratdo4",
                        "password",
                        LocalDate.of(2004, 4, 25),
                        "Keith",
                        "male",
                        List.of(
                                createPhoto("fratdog.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "woofman4",
                        "password",
                        LocalDate.of(1999, 4, 25),
                        "Ian",
                        "male",
                        List.of(
                                createPhoto("woofman.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "divadiva4",
                        "password",
                        LocalDate.of(1996, 4, 25),
                        "Diva",
                        "female",
                        List.of(
                                createPhoto("divadiva.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                ),
                createAppUser(
                        "doofy4",
                        "password",
                        LocalDate.of(2003, 4, 25),
                        "Doofy",
                        "male",
                        List.of(
                                createPhoto("doofy.png", true)
                        ),
                        "A charming and stylish pup who enjoys the finer things in life, like long naps and gourmet treats. Always ready for an elegant adventure.",
                        "Fashion, fine dining (dog-friendly, of course), and cozying up on a plush blanket.",
                        "A sophisticated companion who shares my love for peaceful evenings and an appreciation for gourmet kibble.",
                        "Pawshville",
                        "Snoutopia"
                )
        );

        appUserRepository.saveAll(testUsers);
    }

    private AppUser createAppUser(
            String username,
            String password,
            LocalDate dateOfBirth,
            String knownAs,
            String gender,
            List<Photo> photos,
            String introduction,
            String interests,
            String lookingFor,
            String city,
            String country) {

        AppUser user = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .dateOfBirth(dateOfBirth)
                .knownAs(knownAs)
                .gender(gender)
                .created(LocalDateTime.now().minusDays(30))
                .lastActive(LocalDateTime.now())
                .introduction(introduction)
                .interests(interests)
                .lookingFor(lookingFor)
                .city(city)
                .country(country)
                .photos(photos)
                .build();

        // Set the reverse relationship
        photos.forEach(photo -> photo.setAppUser(user));

        return user;
    }

    private Photo createPhoto(String filename, boolean isMain) {
        return Photo.builder()
                .url(imageUrlPrefix + filename)
                .isMain(isMain)
                .build();
    }
}
