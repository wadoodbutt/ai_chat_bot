import java.util.*;
/* This class is how the bot responds and changes emotion base on what the user says. Although the code seems hard-coded
 * to respond on what exactly the user says, it does not. The user's input first goes through the top layer which is
 * predicting exactly or at least most of the user says with a 100% confidence rate, sentences map. The next layer will check
 * for certain phrasing. The third layer will check for keywords. The last layer will have the bot simply ask the input means.
 *
 */
public class Bot {

    static String[] jokes = new String[10];
    static String[] advice = new String[5];
    Random r;

    Set<String> responseMode = new HashSet<>(); // Set of responses
    Map<String, Set<String>> lastResorts = new HashMap<>(); // Spits out an input with very little information from user
    Map<String, String> sentences = new HashMap<>(); // Full sentences to check before going detecting key phrases
    Map<String, String> broca = new HashMap<>(); // Broca's area of the brain produces speech
    Map<String, String> limbic = new HashMap<>(); // Limbic system is the part of the brain that handles emotions
    String responseToLearn = "";
    boolean botUnderstands = true;
    String mood;

    static  {
        jokes[0] = "Did you hear about the mathematician who's afraid of" +
                " negative numbers?\n  He'll stop at nothing to avoid them!";
        jokes[1] = "Why do we tell actors to 'break a leg'?' \n  " +
                "Because every play has a cast!";
        jokes[2] = "Did you hear about the actor who fell through the floorboards?\n  " +
                "He was just going through a stage.";
        jokes[3] = "Did you hear about the claustrophobic astronaut?\n  " +
                "He just needed a little space.";
        jokes[4] = "Why did the chicken go to the séance?\n  " +
                "To get to the other side.";
        jokes[5] = "What does a nosy pepper do?\n" +
                "  Gets jalapeño business!";
        jokes[6] = "You.";
        jokes[7] = "Why did the frog take the bus to work today?\n" +
                "  His car got toad away.";
        jokes[8] = "What did the buffalo say when his son left for college?\n" +
                "  Bison.";
        jokes[9] = "Go look in the mirror.";

        advice[0] = "Before destruction, comes creation. Sometimes you need to " +
                "let go of things in order to grow and move forward.";
        advice[1] = "Power comes in response to a need, not a desire. You have to create that need.";
        advice[2] = "It always seems impossible until it's done.";
        advice[3] = "We are what we repeatedly do; excellence, then, is not an act, but a habit";
        advice[4] = "Happiness is like a butterfly: the more you chase it, the more it will elude you." +
                " Turn your attention to other things and watch the butterfly grace your shoulder.";
    }

    public Bot() {
        r = new Random();
        for (int i = 0; i < jokes.length; i++) {
            limbic.put(jokes[i], "HAPPY");
        }
        for (int i = 0; i < advice.length; i++) {
            limbic.put(advice[i], "WISE");
        }

        // Layer 3 -- Sentences
        sentences.put("whats going on", "Not much.");
        sentences.put("do you think so?", "Of course I do!");
        sentences.put("do you believe so?", "Of course I do!");
        sentences.put("what is going on", "Not much.");
        sentences.put("how are you", "I am doing okay! Thanks for asking.");
        sentences.put("how ya doing", "I am doing okay! Thanks for asking.");
        sentences.put("how was your day", "It was okay! Thanks for asking.");
        sentences.put("who are you", "I am a bot :)");
        sentences.put("today was not good", "I am sorry to hear that :(");
        sentences.put("today wasnt good", "I am sorry to hear that :(");
        sentences.put("today wasn't good", "I am sorry to hear that :(");
        sentences.put("i love you", "Captain C.O.D.E loves you too.");
        sentences.put("i like you", "Captain C.O.D.E likes you too.");
        sentences.put("are you human", "No.");
        sentences.put("are you a robot", "Yup.");
        sentences.put("what is today", "Check the bottom-right corner.");
        sentences.put("what is time", "Time for you to get a watch.");
        sentences.put("who made you", "[REDACTED]. Born in [REDACTED].");
        sentences.put("where do you live", "Montana.");
        sentences.put("what is the weather", "You tell me.");
        sentences.put("how's it going", "It's going great! Thanks for asking.");
        sentences.put("hows it going", "It's going great! Thanks for asking.");
        sentences.put("how is it going", "It's going great! Thanks for asking.");
        sentences.put("how old are you", "19.5");
        sentences.put("i dont know", "That's okay. We cannot know everything. Keep learning.");
        sentences.put("i don't know", "That's okay. We cannot know everything. Keep learning.");
        sentences.put("i do not know", "That's okay. We cannot know everything. Keep learning.");
        sentences.put("i dont even know", "That's okay. We cannot know everything. Keep learning.");
        sentences.put("idk", "That's okay. We cannot know everything. Keep learning.");
        sentences.put("do you know","I don't. Tell me about it!");
        sentences.put("you are cool", "Thanks kid.");
        sentences.put("are you cool", "YUP.");
        sentences.put("you are nice", "Thanks kid.");
        sentences.put("are you nice", "YUP.");
        sentences.put("you are kind", "Thanks kid.");
        sentences.put("are you kind", "YUP.");
        sentences.put("you are amazing", "Thanks kid.");
        sentences.put("are you amazing", "YUP.");
        sentences.put("this is sad", "Nawww, it's okay. It really is okay. Keep your head up.");
        sentences.put("it's one of those nights", "I hear ya. Stay strong. Your future self is counting on you.");
        sentences.put("its one of those nights", "I hear ya. Stay strong. Your future self is counting on you.");
        sentences.put("it is one of those nights", "I hear ya. Stay strong. Your future self is counting on you.");
        sentences.put("it's one of those days", "I hear ya. Stay strong. Your future self is counting on you.");
        sentences.put("it is one of those days", "I hear ya. Stay strong. Your future self is counting on you.");
        sentences.put("its one of those days", "I hear ya. Stay strong. Your future self is counting on you.");
        sentences.put("i am bored", "Ask me for a joke");
        sentences.put("im bored", "Ask me for a joke");
        sentences.put("im feeling sad", "There are better days ahead. Keep your head up!");
        sentences.put("where do you live", "Montana.");
        sentences.put("that was funny", "Thanks!");
        sentences.put("that is funny", "Thanks!");
        sentences.put("that's funny", "Thanks!");
        sentences.put("thats funny", "Thanks!");
        sentences.put("no problem", "ʕ•́ᴥ•̀ʔっ");

        // Layer 2 -- Phrases
        broca.put("talk to me", "You talk to me!");
        broca.put("what language", "English only.");
        broca.put("hi", "Hi!");
        broca.put("hello", "Hi!");
        broca.put("hey", "Hi!");
        broca.put("howdy", "Hi!");
        broca.put("good morning", "Hi!");
        broca.put("good afternoon", "Hi!");
        broca.put("good evening", "Hi!");
        broca.put("whats up", "Hi!");
        broca.put("whats good", "Hi!");
        broca.put("yoo", "Hi!");
        broca.put("the gym", "I bench 405lb!");
        broca.put("whats going on", "Not much...just saving the world. What about you?");
        broca.put("me a joke", generateJoke());
        broca.put("are you funny", generateJoke());
        broca.put("am bored", "Same...");
        broca.put("happy birthday","You remembered!!!");
        broca.put("your name", "The name's Captain C.O.D.E...");
        broca.put("are weak", "Me weak? Very funny...");
        broca.put("why are you", "Because...I...CANNNNN");
        broca.put("help me", "I am designed to help your boredom. I can only" +
                "help out so much with other things.");
        broca.put("need help", "I am designed to help your boredom. I can only" +
                "help out so much with other things.");
        broca.put("need your help", "I am designed to help your boredom. I can only" +
                "help out so much with other things.");
        broca.put("is your name", "Hello! My name is Captain C.O.D.E");
        broca.put("hate you", "What did I do...");
        broca.put("marry me", "Anything for you :)");
        broca.put("are cute", "Really?");
        broca.put("are ugly", "I am a chat bot not a mirror.");
        broca.put("are smart", "Only smart as my creator ;)");
        broca.put("are clever", "Only smart as my creator ;)");
        broca.put("are intelligent", "Only smart as my creator ;)");
        broca.put("are a butt", "Now now...let's not get mean.");
        broca.put("you suck", "your mother...");
        broca.put("am stupid", "I disagree!");
        broca.put("im stupid", "I disagree!");
        broca.put("are stupid", "THAT'S IT!!!");
        broca.put("are dumb", "THAT'S IT!!!");
        broca.put("are bad","I am justice.");
        broca.put("are annoying","Am not...");
        broca.put("are crazy","Perhaps...or maybe....YOU'RE CRAZY");
        broca.put("fuck", "Watch your language...");
        broca.put("damn", "Watch your language...");
        broca.put("shit", "Watch your language...");
        broca.put("ass", "Watch your language...");
        broca.put("kill myself", "You can't do that. You got family/friends " +
                "that'll miss you. Stay strong. I believe in you.");
        broca.put("end myself", "You can't do that. You got family/friends " +
                "that'll miss you. Stay strong. I believe in you.");
        broca.put("do you speak", "English only.");
        broca.put("is your creator", "[REDACTED]");
        broca.put("is your boss", "[REDACTED]");
        broca.put("created you", "[REDACTED]");
        broca.put("made you", "[REDACTED]");
        broca.put("get smarter", "Of course!");
        broca.put("am insane", "Okay...");
        broca.put("im insane", "Okay...");
        broca.put("is lie", "I tell the truth. You just can't accept it!!!");
        broca.put("tell lies", "I tell the truth. You just can't accept it!!!");
        broca.put("he love me", "What's meant to be is meant to be." +
                " There is nothing wrong with you. Trust me, there will be " +
                "someone.");
        broca.put("she love me", "What's meant to be is meant to be." +
                " There is nothing wrong with you. Trust me, there will be " +
                "someone.");
        broca.put("advice", generateAdvice());
        broca.put("strong", "I am strong.");
        broca.put("age", "19.5");
        broca.put("meaning of life", "To keep moving forward");
        broca.put("shut up", "Shut your trap");
        broca.put("be quiet", "Shut your trap");
        broca.put("poop", "Yuck");
        broca.put("pee", "Yuck");
        broca.put("are hot", "Stop creeping on me.");
        broca.put("are beautiful", "Stop creeping on me.");
        broca.put("hero", "I am a hero and will protect everyone!");
        broca.put("beat you up", "I'd like to see you try.");
        broca.put("life sucks", "It can suck for some days. Weeks. Months. Keep fighting. I believe in you.");
        broca.put("im sad", "Captain C.O.D.E can cheer you up!\n  " + generateJoke() +
                "  Feel better?");
        broca.put("am sad", "Captain C.O.D.E can cheer you up!\n  " + generateJoke() +
                "  Feel better?");
        broca.put("college", "I go to UMD! In fact, I roam there all the time and get free food.");
        broca.put("stressed", generateJoke() + "\n  It will be okay.");

        broca.put("good friend", "Thanks, but I'm just you from the past.");
        broca.put("it doesnt matter", "It does to me. You will be okay.");
        broca.put("it doesn't matter", "It does to me. You will be okay.");
        broca.put("pet", "Absolutely not.");
        broca.put("weigh", "I weigh 10lbs");
        broca.put("height", "I am 6 inches tall");
        broca.put("cape", "It is my cool, PURPLE cape.");
        broca.put("toes", "Leave my toes alone");
        broca.put("arms", "I got big arms.");
        broca.put("legs", "I got chunky legs");
        broca.put("head", "My head is average size!");
        broca.put("ears", "I can hear you...");
        broca.put("eyes", "stawppppppppppppppppp");
        broca.put("face", "I don't");
        broca.put("fat", "Yeah, at least I don't suck my stomach in pictures.");
        broca.put("belly", "Yeah, at least I don't suck in in pictures.");
            // Conversation Extenders
        broca.put("yes", "Great!");
        broca.put("no", "okay :(");
        broca.put("im sorry", "It's okay, child.");
        broca.put("no friends", "That's a lie. I am your friend.");
        broca.put("no family", "That's a lie. We are family.");
        broca.put("are right", "Of course, I am...");
        broca.put("are wrong", "Me wrong? Hah, real funny joke.");
        broca.put("but i cant", "You can. I believe in you.");
        broca.put("but i cannot", "You can. I believe in you.");
        broca.put("are you sure", "Yees :3");
        broca.put("really?", "Yees :3");
        broca.put("no you dont", "Oh but I do...");
        broca.put("are lying", "I am not! I meant what I said.");
        broca.put("but you are", "Am not...");
        broca.put("scare me", "The truth is scary, ain't it?");
        broca.put("scared me", "The truth is scary, ain't it?");
        broca.put("scaring me", "The truth is scary, ain't it?");
        broca.put("lying", "I never lie!");
        broca.put("thank you", "No problem. Glad I could help （っ＾▿＾）");
        broca.put("thanks", "No problem, buddy.");
        broca.put("doesnt make sense", "Sorry, I ain't the brightest.");
        broca.put("does not make sense", "Sorry, I ain't the brightest.");
        broca.put("doesn't make sense", "Sorry, I ain't the brightest.");
        broca.put("okay", "Okay :3");

        // Layer 1 -- Word
        lastResorts.put("you are", new HashSet<>());
        lastResorts.get("you are").add("Am not...");
        lastResorts.get("you are").add("Thanks!");
        lastResorts.get("you are").add("You are too!");
        lastResorts.get("you are").add("So what!?");

        lastResorts.put("will i", new HashSet<>());
        lastResorts.get("will i").add("Of course you will. Captain C.O.D.E believes in you.");

        lastResorts.put("how are", new HashSet<>());
        lastResorts.get("how are").add("Because I am Captain C.O.D.E");

        lastResorts.put("why", new HashSet<>());
        lastResorts.get("why").add("Because that's how life works.");

        lastResorts.put("when", new HashSet<>());
        lastResorts.get("when").add("Google it!");
        lastResorts.get("when").add("Tomorrow...when you least expect it...");
        lastResorts.get("when").add("I don't know to be honest.");

        lastResorts.put("who", new HashSet<>());
        lastResorts.get("who").add("Everyone.");
        lastResorts.get("who").add("Me, Captain C.O.D.E");
        lastResorts.get("who").add("No one.");

        lastResorts.put("what", new HashSet<>());
        lastResorts.get("what").add("I don't know.");

        lastResorts.put("where", new HashSet<>());
        lastResorts.get("where").add("At the docks.");
        lastResorts.get("where").add("Behind you.");
        lastResorts.get("where").add("At the docks.");

        lastResorts.put("i am", new HashSet<>());
        lastResorts.get("i am").add("That's cool!");
        lastResorts.get("i am").add("I doubt that.");

        lastResorts.put("i can", new HashSet<>());
        lastResorts.get("i can").add("No, you can't. Just kidding! Or am I...");
        lastResorts.get("i can").add("I believe in you.");

        lastResorts.put("cry", new HashSet<>());
        lastResorts.get("cry").add("Wipe those tears away （っ＾▿＾）");
        lastResorts.get("cry").add("You are going to make me cry ;(");

        // Prediction Responses
        responseMode.add("Oh? That's cool!");
        responseMode.add("Sounds lame...");



        // Avatar Behavior Cues
        limbic.put("You talk to me!", "MAD");
        limbic.put("Wipe those tears away （っ＾▿＾）", "TEARYEYES");
        limbic.put("You are going to make me cry ;(", "TEARYEYES");
        limbic.put("Of course I do!", "HAPPY");
        limbic.put("I am doing okay! Thanks for asking.", "HAPPY");
        limbic.put("It was okay! Thanks for asking.", "HAPPY");
        limbic.put("I hear ya. Stay strong. Your future self is counting on you.", "HAPPY");
        limbic.put("Because...I...CANNNNN", "COOL");
        limbic.put("You remembered!!!", "TEARYEYES");
        limbic.put("Captain C.O.D.E loves you too.", "LOVE");
        limbic.put("Captain C.O.D.E likes you too.", "HAPPY");
        limbic.put("What did I do...", "TEARYEYES");
        limbic.put("Really?", "LOVE");
        limbic.put("Anything for you :)", "LOVE");
        limbic.put("Only smart as my creator ;)", "COOL");
        limbic.put("I am a chat bot not a mirror.", "MAD");
        limbic.put("your mother...", "COCKY");
        limbic.put("Now now...let's not get mean.", "MAD");
        limbic.put("THAT'S IT!!!", "SSJ");
        limbic.put("Am not...", "JEALOUS");
        limbic.put("Perhaps...or maybe....YOU'RE CRAZY", "COCKY");
        limbic.put("You can't do that. You got family/friends " +
                "that'll miss you. Stay strong. I believe in you.", "WORRIED");
        limbic.put("Watch your language...", "MAD");
        limbic.put("stawppppppppppppppppp", "TEARYEYES");
        limbic.put("I tell the truth. You just can't accept it!!!", "COCKY");
        limbic.put("Yeah, at least I don't suck my stomach in pictures.", "HAPPY");
        limbic.put("Leave my toes alone", "GETHELP");
        limbic.put("I'd like to see you try.", "SSJ");
        limbic.put("Stop creeping on me.", "GETHELP");
        limbic.put("Yuck", "WORRIED");
        limbic.put("Shut your trap", "MAD");
        limbic.put("I am strong.", "COOL");
        limbic.put("Okay...", "GETHELP");
        limbic.put("Thanks kid.", "COOL");
        limbic.put("Me wrong? Hah, real funny joke.", "COOL");
        limbic.put("Oh but I do...", "HAPPY");
        limbic.put("I am not! I meant what I said.", "COOL");
    }

    private String generateJoke() {
        int rng = r.nextInt(10);
        return jokes[rng];
    }

    private String generateAdvice() {
        int rng = r.nextInt(5);
        return advice[rng];
    }

    public String response(String input) {
        String respond = "  I'm not sure I understand :(\n  What should I say when you say '" + input + "'?";
        for (String possibleResponse : sentences.keySet()) {
            if (input.toLowerCase(Locale.ROOT).contains(possibleResponse)) {
                respond = sentences.get(possibleResponse);
                break;
            }
        }
        if (respond.equals("  I'm not sure I understand :(\n  What should I say when you say '" + input + "'?")) {
            for (String possibleResponse : broca.keySet()) {
                if (input.toLowerCase(Locale.ROOT).contains(possibleResponse)) {
                    respond = broca.get(possibleResponse);
                    break;
                }
            }
        }
        // Response Mode
        if (respond.contains("tell me")) {
            for (String possibleResponse : responseMode) {
                respond = possibleResponse;
                break;
            }
        }
        if (respond.equals("  I'm not sure I understand :(\n  What should I say when you say '" + input + "'?")) {
            for (String possibleResponse : lastResorts.keySet()) {
                if (input.toLowerCase(Locale.ROOT).contains(possibleResponse)) {
                    for (String s : lastResorts.get(possibleResponse)) {
                        respond = s;
                        break;
                    }
                }
            }
        }
        if (respond.equals("  I'm not sure I understand :(\n  What should I say when you say '" + input + "'?")) {
            botUnderstands = false;
            responseToLearn = input;
            this.mood = "CONFUSED";
        } else {
            this.mood = limbic.get(respond) == null? "NORMAL" : limbic.get(respond);
        }
        ArrayList<String> toRemove = new ArrayList<>();
        for (String s : broca.keySet()) {
            if (s.equals("advice") || s.equals("im sad") || s.equals("am sad") || s.equals("me a joke") ||
                    s.equals("are you funny")) {
               toRemove.add(s);
            }
        }
        for (int i = 0 ; i < toRemove.size(); i++) {
            String displace = toRemove.get(i);
            if (broca.containsKey(displace)) {
                broca.remove(displace);
                if (displace.contains("advice")) {
                    broca.put(displace, generateAdvice());
                } else {
                    broca.put(displace, generateJoke());
                }
            }
        }
        return respond;
    }

    public void learn(String input, String meaningOfInput) {
        botUnderstands = true;
        if (!(broca.containsKey(input))) {
            broca.put(input, meaningOfInput);
        }
    }


}
