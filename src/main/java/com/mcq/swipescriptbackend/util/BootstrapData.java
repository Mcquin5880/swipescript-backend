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
import java.util.Set;

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

                // TEST ADMIN
                createAppUser(
                        "ADMIN",
                        "password",
                        LocalDate.of(1988, 6, 23),
                        "Mr Admin",
                        "male",
                        List.of(createPhoto("default1.png", true)),
                        "testing",
                        "testing",
                        "123",
                        "Chattanooga",
                        "Tennessee"
                ),

                // MALE USERS
                createAppUser(
                        "barkley_paws",
                        "barkbark123",
                        LocalDate.of(1988, 3, 15),
                        "Barkley",
                        "male",
                        List.of(createPhoto("barkley.png", true), createPhoto("barkley2.png", false)),
                        "Part-time stick connoisseur, full-time tail-wagger. I’m the dog you call when you need a partner for spontaneous park adventures, an accomplice for late-night garbage raids, or a cozy couch buddy for lazy Sundays. Warning: My zoomies may knock over small children.",
                        "Selective hearing (only applies to the word 'bath'), proudly muddy paws, and a deep distrust of the vacuum cleaner. I also moonlight as a mailman deterrent and part-time squirrel chaser.",
                        "Looking for someone who loves rolling in the grass, long walks under the moonlight, and can handle a tug-of-war champion. Bonus points if you have a bottomless treat jar and know how to throw a Frisbee like a pro.",
                        "Puppytown",
                        "California"
                ),
                createAppUser(
                        "rufus_rascal",
                        "rufflife123",
                        LocalDate.of(2006, 11, 8),
                        "Rufus",
                        "male",
                        List.of(createPhoto("rufus.png", true)),
                        "Certified garbage bin inspector and self-proclaimed ‘king of the couch.’ When I’m not sneaking snacks, you’ll find me mastering the art of strategic napping. My philosophy? If it smells good, eat it. If it moves, chase it.",
                        "Unrolling toilet paper, stealing socks, and mysteriously making your sandwich disappear when you’re not looking. I also enjoy moonlit howling sessions and dramatic belly flops into puddles.",
                        "Looking for a partner in crime who loves late-night snack raids and isn’t afraid to get their paws dirty. Bonus points if you know how to open the fridge.",
                        "Trashcan Alley",
                        "Texas"
                ),
                createAppUser(
                        "sir_barksalot",
                        "topdogCEO",
                        LocalDate.of(2001, 7, 20),
                        "Barksalot",
                        "male",
                        List.of(createPhoto("barksalot.png", true), createPhoto("barksalot2.png", false)),
                        "Distinguished entrepreneur, bone investor, and CEO of FetchCorp™. When I’m not closing deals or negotiating treaties at the local dog park, you’ll find me enjoying a fine chew toy in my penthouse. A true professional by day, and a charmer by night.",
                        "Micromanaging squirrels, winning at tug-of-war, and barking at delivery drivers to ensure package security. I’m also a big fan of power naps and gourmet kibble tastings.",
                        "Looking for a classy companion who enjoys long walks in the city, rooftop sunset views, and has a passion for sniffing out new opportunities. Bonus points if you can keep up with my busy schedule and don’t mind the occasional boardroom bark.",
                        "Pawston",
                        "Florida"
                ),
                createAppUser(
                        "woofman",
                        "password",
                        LocalDate.of(1999, 4, 25),
                        "Ian",
                        "male",
                        List.of(createPhoto("woofman.png", true), createPhoto("woofman2.png", false)),
                        "Certified gym bro and canine fitness influencer. Whether it’s hitting the dog park for sprints or maxing out reps with my chew toy dumbbells, I live for the grind. My motto? 'Every day is leg day when you’re a quadruped.'",
                        "Spilling my protein shake all over the floor, chasing my tail for cardio, and barking at mirrors (I mean, who is that handsome dog?). Also, I’ve been known to hog the tennis ball machine at the park.",
                        "Looking for a workout partner who can keep up with my energy and doesn’t mind sharing their snacks post-workout. Bonus points if you can throw a Frisbee far enough for me to break a sweat!",
                        "Flexington",
                        "New York"
                ),
                createAppUser(
                        "doug_the_barkeologist",
                        "digging4bones",
                        LocalDate.of(1988, 9, 14),
                        "Doug",
                        "male",
                        List.of(createPhoto("doug.png", true)),
                        "Renowned barkeologist and professional digger. I’ve unearthed over 100 buried bones and a mysterious squeaky toy believed to be from the ancient Chewnasty civilization. When I’m not on an expedition, you’ll find me mapping my backyard for hidden treasures.",
                        "Accidentally digging up flower beds, chasing the neighbor’s cat (purely for cardio), and gnawing on historical artifacts. I also host a weekly seminar on ‘Bone Preservation Techniques’ at the local park.",
                        "Looking for someone who loves adventure, appreciates a good dirt bath, and doesn’t mind sharing their snacks while stargazing. Bonus points if you have a backyard with excavation potential.",
                        "Digsylvania",
                        "Illinois"
                ),
                createAppUser(
                        "professor_paws",
                        "smartywoof42",
                        LocalDate.of(1970, 2, 10),
                        "Einstein",
                        "male",
                        List.of(createPhoto("professor_paws.png", true)),
                        "World-renowned canine scholar and inventor of the ‘Pavlovian Fetch Theorem.’ When I’m not delivering riveting lectures at Barkvard University, you’ll find me buried in my books or experimenting with new treat-delivery systems. They say I’m the goodest boy in academia, and who am I to argue with data?",
                        "Correcting your grammar with a wag of my tail, chewing on a squeaky abacus, and teaching advanced tail-chasing techniques. I also enjoy pondering the mysteries of the universe while basking in sunbeams.",
                        "Seeking an intellectual equal who can appreciate long discussions about quantum bones and the ethics of leash-free zones. Bonus points if you love library dates and have a fascination with squeaky toys.",
                        "Scholartown",
                        "Pennsylvania"
                ),
                createAppUser(
                        "rocky_howliday",
                        "howlin4life",
                        LocalDate.of(2005, 10, 5),
                        "Rocky",
                        "male",
                        List.of(createPhoto("rocky.png", true)),
                        "Lead howler and front-dog of the legendary band ‘The Rolling Bones.’ When I’m not shredding on my squeaky guitar, I’m living the rock-and-roll lifestyle: chasing fame, Frisbees, and my tail. Life’s a concert, and I’m here to bring the woofing thunder.",
                        "Chewing on drumsticks (literally), howling at the moon, and stealing the spotlight at karaoke night. I also dabble in songwriting—my latest hit, ‘Treats and Leashes,’ is climbing the charts.",
                        "Looking for someone who can match my energy and isn’t afraid to live life off-leash. Bonus points if you’re a great backup howler or know your way around a squeaky tambourine.",
                        "Howlington",
                        "Georgia"
                ),
                createAppUser(
                        "fratdog",
                        "beerandbones04",
                        LocalDate.of(2004, 4, 25),
                        "Keith",
                        "male",
                        List.of(createPhoto("fratdog.png", true), createPhoto("fratdog2.png", false)),
                        "Life of the pawty and president of the ‘Barkappa Alpha’ fraternity. My specialties include keg stands (with my water bowl), hosting tailgate pawties, and stealing hot dogs off the grill. Don’t let the backward baseball cap fool you—I’ve got a heart as big as my appetite.",
                        "Marking my territory (in style), howling the loudest at karaoke night, and pretending to understand football just to fit in. I also make the best pup-tinis in town—ask anyone at the frat house.",
                        "Looking for a chill pup who loves hanging out on the porch, sneaking bites of pizza crust, and doesn’t mind late-night howls to the moon. Bonus points if you can throw a tennis ball straight after a few rounds of fetch pong.",
                        "Barkademia",
                        "Ohio"
                ),
                createAppUser(
                        "doofy1",
                        "1234woof",
                        LocalDate.of(1983, 4, 25),
                        "Doofy",
                        "male",
                        List.of(createPhoto("doofy.png", true)),
                        "Professional tail chaser and part-time social media newbie. My nephew helped me set up this profile because I thought ‘swipe right’ meant chasing squirrels in a clockwise circle. I may not know how to DM, but I’ll win you over with my goofy grin and endless loyalty.",
                        "Accidentally liking my own photos, barking at autoplay videos, and sharing too many pictures of my food bowl. Also, I once tried to friend-request the vacuum cleaner.",
                        "Looking for someone patient enough to teach me how to tag a photo and kind enough not to laugh when I accidentally post 30 selfies in a row. Bonus points if you like long walks where we both get lost and need GPS to find our way home.",
                        "Techsylvania",
                        "Michigan"
                ),
                createAppUser(
                        "mason_theexplorer",
                        "trailblazer",
                        LocalDate.of(2002, 5, 12),
                        "Mason",
                        "male",
                        List.of(createPhoto("mason.png", true)),
                        "Trailblazer, pawthfinder, and lover of all things wild. From scaling backyard fences to conquering mountain trails, I live for the thrill of adventure. No leash can hold me, no squirrel is too quick, and no puddle is too deep.",
                        "Rolling in mud, barking at deer, and treating every stick like it’s the Holy Grail. I’m also really good at losing your shoes in the name of exploration.",
                        "Looking for a fellow adventurer who doesn’t mind getting their paws dirty and loves the smell of fresh grass and freedom. Bonus points if you can pack a mean trail snack.",
                        "Pawagonia",
                        "North Carolina"
                ),
                createAppUser(
                        "max_marathoner",
                        "run4treats",
                        LocalDate.of(1999, 3, 22),
                        "Max",
                        "male",
                        List.of(createPhoto("max.png", true)),
                        "Cardio king, endurance enthusiast, and the reigning champion of the Pawsville Fun Run three years running (pun intended). I live for the thrill of the race, the wind in my fur, and the sweet sound of paws hitting the pavement.",
                        "Waking you up at 5 AM for a jog, stealing water bottles from your gym bag, and sprinting after squirrels as part of my HIIT training. Also, I’ve been known to rock a sweatband like no other.",
                        "Looking for a running buddy who doesn’t mind chasing after the sunrise and loves a good cool-down stretch. Bonus points if you’ve got a fastball arm for my post-run fetch sessions.",
                        "Joggersville",
                        "Arizona"
                ),
                createAppUser(
                        "charlie_chowhound",
                        "foodie4life",
                        LocalDate.of(1986, 8, 3),
                        "Charlie",
                        "male",
                        List.of(createPhoto("charlie.png", true)),
                        "Culinary connoisseur and self-proclaimed treat sommelier. My days revolve around sniffing out the best snacks, sampling gourmet kibble, and dreaming of the perfect steak-flavored chew toy. Food is my love language, and my tail wags for bacon.",
                        "Begging for table scraps, stealing sandwiches when no one’s looking, and hosting potluck dinners at the dog park (everyone brings bones). I’m also a five-time champion in the annual Pawsville Hot Dog Eating Contest.",
                        "Looking for a partner who shares my love of food and doesn’t mind the occasional bark of approval when the snacks are good. Bonus points if you’re great at finding the best dog-friendly restaurants.",
                        "Chew York",
                        "New Jersey"
                ),
                createAppUser(
                        "captain_barkbeard",
                        "ahoymatey",
                        LocalDate.of(2001, 7, 17),
                        "Captain",
                        "male",
                        List.of(createPhoto("captain.png", true)),
                        "Fearless sea dog, treasure hunter, and captain of the legendary ship ‘The Salty Paw.’ Whether I’m charting new waters or burying bones on a sandy shore, adventure is my middle name. Beware: I bark like a pirate and steal snacks like one too!",
                        "Chewing ropes, chasing seagulls, and sniffing out buried treasure at the park. I’m also a master of the growly sea chanty and can navigate my way to the treat jar with no map.",
                        "Looking for a first mate who loves the ocean breeze, long walks on the beach, and has a steady paw for sharing snacks on stormy seas. Bonus points if you know how to tie a good knot or enjoy a game of fetch on the deck.",
                        "Seadog Bay",
                        "Virginia"
                ),
                createAppUser(
                        "snips_mcfur",
                        "furfades123",
                        LocalDate.of(1984, 11, 9),
                        "Snips",
                        "male",
                        List.of(createPhoto("snips.png", true)),
                        "Master groomer and barber extraordinaire, specializing in fur fades and stylish trims. Whether it’s a pawdicure or a full-on makeover, I’ll make sure you leave looking pawsitively dashing. My motto? Every pup deserves to look their best.",
                        "Sniffing out the latest grooming trends, chasing stray hairs, and giving tail-wagging consultations. I’m also the go-to pup for advice on the perfect fur-care routine (treats are a bonus).",
                        "Looking for someone who appreciates a clean coat, loves bubble baths, and doesn’t mind the occasional fur clippings in their kibble. Bonus points if you enjoy long walks to the groomer supply store.",
                        "Trimtown",
                        "Washington"
                ),
                createAppUser(
                        "baywatch_buddy",
                        "woofguard",
                        LocalDate.of(2000, 6, 10),
                        "Buddy",
                        "male",
                        List.of(createPhoto("buddy.png", true)),
                        "Certified lifeguard, master of belly flops, and professional stick retriever. Whether it’s saving tennis balls from drowning or leading a game of beach fetch, I’m always on duty to keep the shores safe and fun. Sun’s out, paws out!",
                        "Digging moats around sandcastles, barking at beach umbrellas, and performing dramatic slow-motion runs along the shoreline. I’ve also been known to steal flip-flops—strictly for rescue practice, of course.",
                        "Looking for a beach buddy who loves the smell of saltwater, doesn’t mind a little sand in their fur, and enjoys splashing around in the waves. Bonus points if you’re great at tossing Frisbees into the surf.",
                        "Pawtona Beach",
                        "Tennessee"
                ),

                // FEMALE USERS
                createAppUser(
                        "bella_barker",
                        "fetchqueen123",
                        LocalDate.of(2004, 9, 18),
                        "Bella",
                        "female",
                        List.of(createPhoto("bella.png", true)),
                        "5'3\" (on my hind legs), sassy, and always ready to bark at the haters. Obsessed with long walks (as long as they’re in the park, not to the vet) and gourmet kibble. Swipe right if you’re into a gal who knows what she wants—belly rubs and unlimited squeaky toys.",
                        "Chasing tennis balls, posing for Instagram-worthy pics, and stealing socks (only the good ones).",
                        "Looking for a tall, dark, and handsome pup who can keep up with my zoomies and doesn’t mind sharing snacks. Bonus points if your profile includes a picture of you in a bowtie.",
                        "Barklyn",
                        "Indiana"
                ),
                createAppUser(
                        "cocoa_the_chill",
                        "napqueen456",
                        LocalDate.of(1986, 11, 2),
                        "Cocoa",
                        "female",
                        List.of(createPhoto("cocoa.png", true)),
                        "Netflix and chew? Yes, please. I’m your chill girl who loves lazy afternoons, belly rubs, and a good sunbeam to nap in. Not here for drama—just looking for someone to share snacks and sniff the roses with.",
                        "Cuddling, slow walks where I sniff every blade of grass, and side-eyeing squirrels from the comfort of the porch.",
                        "Looking for a laid-back companion who appreciates long naps and doesn’t mind me hogging the comfy spots. Bonus points if you share your blanket and never rush me during walkies.",
                        "Pawmont",
                        "Massachusetts"
                ),
                createAppUser(
                        "lola_luxury",
                        "paws4style",
                        LocalDate.of(2005, 11, 15),
                        "Lola",
                        "female",
                        List.of(createPhoto("lola.png", true)),
                        "I’m a fashion-forward pup who knows how to strut her stuff. Always on the lookout for the latest trends in collars and bandanas. If you’re looking for someone who can match your style, swipe right.",
                        "Modeling my extensive wardrobe, posing for the camera, and stealing the spotlight at dog park meetups. Also, I have a soft spot for spa days (who doesn’t love a good fur blowout?).",
                        "Seeking a gentleman pup who appreciates a diva with a big heart. Must love photo ops, treat picnics, and long walks to the boutique.",
                        "Glamourville",
                        "Missouri"
                ),
                createAppUser(
                        "queen_pawlish",
                        "spaqueen123",
                        LocalDate.of(1994, 10, 15),
                        "Fluffy",
                        "female",
                        List.of(createPhoto("fluffy.png", true)),
                        "Living my best life, one spa day at a time. I enjoy getting pawdicures, relaxing in luxurious doggie day spas, and being the center of attention. My motto? ‘Why walk when you can be carried?’",
                        "Bubble baths, gourmet treats served on a silver platter, and lounging on my custom velvet dog bed. I’m also an avid collector of designer collars.",
                        "Looking for a partner who knows how to treat a lady right—compliments, belly rubs, and breakfast in bed are a must. Bonus points if you come with a chauffeur for our playdates.",
                        "Poshville",
                        "Maryland"
                ),
                createAppUser(
                        "carol",
                        "pass",
                        LocalDate.of(1975, 4, 25),
                        "Carol",
                        "female",
                        List.of(createPhoto("carol1.png", true), createPhoto("carol2.png", false)),
                        "Refined, fabulous, and unapologetically confident. I’m the grand dame of the dog park who knows how to command attention with a wag of her tail. I adore leisurely strolls, good company, and a fine chew toy paired with vintage-style doggy treats.",
                        "Modeling my seasonal wardrobe, attending pawliday parties, and judging poorly executed belly rubs with a look that says, ‘Really?’ I also have a knack for matchmaking—ask anyone who’s ever shared a doghouse with me.",
                        "Looking for a classy gentleman who appreciates a strong, independent lady. Must enjoy candlelit dinners (kibble optional), polite paw-shakes, and sharing the couch without hogging the blankets.",
                        "Canine Creek",
                        "Wisconsin"
                ),
                createAppUser(
                        "dunder_dog",
                        "beetsbears123",
                        LocalDate.of(2002, 3, 19),
                        "Pamela",
                        "female",
                        List.of(createPhoto("pamela.png", true)),
                        "World’s Best Dog. Lover of belly rubs and binge-watching *The Office* for the millionth time (Team Pam, obviously). I might not fetch beets from Schrute Farms, but I’ll fetch your heart.",
                        "Quoting my favorite *The Office* lines, chasing tailspins that rival Jim’s pranks, and barking along to the theme song. Also known to host *The Office* trivia nights at the dog park.",
                        "Looking for my Jim—someone who appreciates long walks in the park, comfy naps on the couch, and sharing snacks while we laugh at Michael Scott’s antics. Bonus points if you know how to make paw-some chili (without spilling it).",
                        "Scruffton",
                        "Colorado"
                ),
                createAppUser(
                        "readerspup",
                        "books4life",
                        LocalDate.of(1999, 9, 15),
                        "Margaret",
                        "female",
                        List.of(createPhoto("margaret.png", true)),
                        "A proud bookworm who always has her nose buried in a novel (or at least trying to chew on one). Whether it's a rainy day or a sunny one, you'll find me curled up with a classic or sniffing out new arrivals at the library. My dream? To star in my own storybook romance.",
                        "Chewing on leather-bound first editions, chasing pages caught in the wind, and reenacting scenes from Jane Pawsten novels. Also a big fan of cozy reading nooks and warm blankets.",
                        "Looking for a fellow literary enthusiast who loves slow evenings by the fireplace and can handle my quirky habit of quoting Shakespeare in barks. Bonus points if you bring snacks to our book club.",
                        "Booktown",
                        "Minnesota"
                ),
                createAppUser(
                        "daisy_reads",
                        "chapterone",
                        LocalDate.of(2001, 5, 15),
                        "Daisy",
                        "female",
                        List.of(createPhoto("daisy.png", true)),
                        "Certified bookworm and librarian of the local dog park. When I’m not curled up with a squeaky novel, you’ll find me organizing storytime with the puppies or debating the merits of chewable vs. non-chewable bookmarks. My tail wags for literary adventures and deep, philosophical barks.",
                        "Sniffing through old books, writing short ‘tail’ stories, and hosting book club meetings where every member howls their opinions. I also dabble in translating barks into prose.",
                        "Looking for a partner who loves cozy evenings with a good book and can keep up with my endless curiosity. Bonus points if you know how to brew the perfect puppuccino.",
                        "Readerville",
                        "South Carolina"
                ),
                createAppUser(
                        "dazzling_danny",
                        "socialstar123",
                        LocalDate.of(2004, 7, 8),
                        "Danny",
                        "female",
                        List.of(createPhoto("danny.png", true)),
                        "Caked-up queen and the life of every pawty. I’m a TikTok sensation, with the most wag-tastic dance moves you'll ever see. When I’m not shaking my tail on the dance floor, I’m perfecting my pout for selfies or binge-scrolling through Petstagram.",
                        "Posting flawless selfies, creating viral dance trends, and barking in perfect harmony to my favorite club tracks. I also enjoy late-night puppuccinos and adding rhinestones to my collar.",
                        "Looking for a fellow social media star or someone who can handle a diva with 10K followers. Bonus points if you can keep up with my dance moves and don’t mind being tagged in every photo.",
                        "Pawlywood",
                        "Alabama"
                ),
                createAppUser(
                        "lady_pawsington",
                        "diamondsareforever",
                        LocalDate.of(1991, 9, 12),
                        "Penelope",
                        "female",
                        List.of(createPhoto("penelope.png", true)),
                        "The epitome of grace and charm, I’m a sophisticated lady who loves the finer things in life. Whether it’s lounging on a velvet cushion or being pampered at the spa, I believe in living like royalty. My favorite accessory? A dazzling diamond-studded collar.",
                        "Afternoon tea parties, strutting down the park runway in my custom designer leash, and getting daily paw-dicures. I’m also known for my signature twirl when I’m feeling fabulous.",
                        "Looking for a gentleman who knows how to treat a lady—long walks in the park, romantic dinners (with gourmet kibble), and someone who doesn’t mind carrying my shopping bags. Bonus points if you have a taste for luxury!",
                        "Pawington",
                        "Kentucky"
                ),
                createAppUser(
                        "luna_shadows",
                        "darkness123",
                        LocalDate.of(2000, 10, 31),
                        "Luna",
                        "female",
                        List.of(createPhoto("luna.png", true)),
                        "Wandering through the shadows of life, I find beauty in the moonlight and solace in the howls of the night. Some may call me a lone wolf, but I prefer to think of myself as a misunderstood artist. My bark echoes the melancholia of a thousand rainy days.",
                        "Staring out of windows dramatically, curling up in cozy blankets with moody music, and collecting bones (strictly for aesthetic purposes). Occasionally seen pawing through vintage collars at thrift shops.",
                        "Looking for a soul who understands that true connection lies in shared silences and meaningful howls at the full moon. Bonus points if your coat is as black as my soul (or at least as dark as my paw pads).",
                        "Midnight Hollow",
                        "Oregon"
                ),
                createAppUser(
                        "sassy_sasha",
                        "woofqueen99",
                        LocalDate.of(2004, 8, 21),
                        "Sasha",
                        "female",
                        List.of(createPhoto("sasha.png", true)),
                        "Energetic, bold, and always the life of the dog park. Whether I’m mastering new tricks, organizing pup playdates, or strutting my stuff at the agility course, I bring flair to everything I do. My tail wags with enthusiasm for all things fun and adventurous!",
                        "Playing fetch with frisbees, organizing impromptu howling sessions, and keeping my coat shiny enough to dazzle the crowd at puppy parades. Oh, and I make the best mud pies in the backyard—just ask my neighbors.",
                        "Looking for someone who can match my energy, share snacks during picnics, and doesn’t mind a little friendly competition during tug-of-war. Bonus points if you can teach me a few cool tricks or have a playlist for doggy dance-offs!",
                        "Woofington",
                        "Oklahoma"
                ),
                createAppUser(
                        "missy",
                        "password",
                        LocalDate.of(2006, 4, 25),
                        "Missy",
                        "female",
                        List.of(createPhoto("missy.png", true)),
                        "A paws-itively chic lady who’s always ready to sip a puppuccino in style, but don’t let that fool you — I’m just as comfortable rolling in the grass or stealing your socks when you're not looking.",
                        "Cuddling in my plush blanket fort, chasing my tail in circles for hours, and pretending not to hear when it's time for a bath. Oh, and I’m basically a treat connoisseur. I know my kibble like a sommeli-dog!",
                        "Looking for someone who’s as down for a lazy Sunday as they are for an impromptu zoomie race around the house. Bonus points if you have a treat jar that's bottomless and an appreciation for the fine art of belly rubs.",
                        "Pawshville",
                        "Nevada"
                ),
                createAppUser(
                        "divadiva",
                        "password",
                        LocalDate.of(1996, 4, 25),
                        "Diva",
                        "female",
                        List.of(createPhoto("diva.png", true)),
                        "I’m the queen of the castle and the star of every room. My day consists of sunbathing, impeccable grooming, and rejecting treats that aren't organic. I don't 'fetch'—I supervise. My paws are my crown.",
                        "Exquisite taste in everything—only the finest plush toys, the most exclusive belly rubs, and gourmet meals served at exactly 3pm. If you’re not at least six feet tall, don't even bother looking at my photos.",
                        "Seeking someone who knows their worth and doesn’t mind bowing down to royalty. Must love luxury, adore pampering, and be ready for spontaneous 'catwalk' sessions at the dog park. Bonus points if you can get me the perfect Instagram filter.",
                        "Pawshville",
                        "Mississippi"
                ),
                createAppUser(
                        "whiskers_wagging",
                        "squirrelhunter99",
                        LocalDate.of(2006, 6, 15),
                        "Whiskers",
                        "female",
                        List.of(createPhoto("whiskers.png", true)),
                        "I’m the fierce queen of the backyard, expert squirrel chaser, and the leader of the local pack. I might act aloof, but I’ve got a heart of gold... and a nose for mischief. You’ll often find me rolling in something unidentifiable, but hey, that's part of the charm.",
                        "Digging holes, barking at nothing (or so you think), and staring longingly out the window while plotting my next great escape. I'm a master of the 'puppy dog eyes' and will convince you to share your fries, no questions asked.",
                        "Looking for a partner who’s adventurous, doesn’t mind a little dirt, and appreciates a good squirrel chase. Bonus points if you know how to open the fridge or you have a backyard I can explore endlessly. If you’re lucky, I’ll even let you pet me—on MY terms, of course.",
                        "Mischiefville",
                        "Arkansas"
                ),

                // DEFAULT
                createAppUser(
                        "test1",
                        "password",
                        LocalDate.of(2000, 1, 1),
                        "John",
                        "male",
                        List.of(createPhoto("default1.png", true)),
                        "A simple pup with a love for belly rubs and fetch. Life’s better with treats and a comfy spot by the window.",
                        "Fetch, naps, and belly rubs.",
                        "Looking for someone who enjoys the simple joys in life, like a good game of fetch and a cozy nap.",
                        "DefaultTown",
                        "DefaultState"
                ),
                createAppUser(
                        "test2",
                        "password",
                        LocalDate.of(2000, 1, 1),
                        "Jane",
                        "female",
                        List.of(createPhoto("default2.png", true)),
                        "A sweet and playful pup who loves chasing her tail and being pampered. Ready for a good walk and even better treats.",
                        "Running, treats, and cuddles.",
                        "Looking for someone to share adventures, snacks, and lazy afternoons.",
                        "DefaultTown",
                        "DefaultState"
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
            String state) {

        String role = username.equals("ADMIN") ? "ADMIN" : "USER";

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
                .state(state)
                .photos(photos)
                .roles(Set.of(role))
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
