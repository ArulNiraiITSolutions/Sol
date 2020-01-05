package com.payilagam.oli.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.payilagam.oli.R;

import java.util.Arrays;
import java.util.List;

public class LettersUtil {

    public static long mLastClickTime = 0;
    private final static String[][] wordsList = {{"அம்மா","அப்பா"},{"ஆசை","ஆடு"},{"இசை","இலை"},{"ஈசல்","ஈட்டி"},{"உடை","உலகம்"},{"ஊசி","ஊர்"},{"எடை","எட்டு"},{"ஏணி","ஏர்"},{"ஐந்து","ஐயா"},{"ஒருமை","ஒலி"},{"ஓடு","ஓடை"},{"ஔவியம்","ஔவை"}};

    private final static String[][] wordsList_en = {{"Amma (Mother)","Appa (Father)"},{"Asai (Desire)","Aadu (Goat)"},{"Isai (Music)","Ila (Leaf)"},{"Eesal (Spammers)","Eeti (Javelin)"},{"Udai (Dress)","Ulagam (World)"},{"Usi (Injection)","Oor (Trail)"},{"Edai (Weight)","Ettu (Eight)"},{"Eni (Ladder)","Er (Air)"},{"Iynthu (Five)","Iya (Sir)"},{"Orumy (Singular)","Oli (Sound)"},{"Odu (Shell)","Odai (Feed)"},{"Auviyam (Envy)","Auvai (Poet Name)"}};

    private final static String[][] meyeluthu = {{"காக்கை","சுக்கு"},{"தங்கம்","சங்கு"},{"தச்சன்","பூச்சி"},{"பஞ்சு","மஞ்சள்"},{"தட்டு","பட்டு"},{"கண்","வண்டு"},{"தாத்தா","நத்தை"},{"பந்து","ஆந்தை"},{"உப்பு","சிறப்பு"},{"பாதம்","பாம்பு"},{"நாய்","காய்"},{"தேர்","மலர்"},{"பால்","பல்லி"},{"எவ்வாறு","செவ்வாய்"},{"தமிழ்","யாழ்"},{"தேள்","பள்ளி"},{"பயிற்சி","சிற்பம்"},{"மீன்","நன்றி"}};

    private final static String[][] meyeluthu_en = {{"Kakkai (Crows)","Sukku (Dried ginger)"},{"Thangam (Gold)","Sangu (Conch)"},{"Thachan (Carpenter)","Poochi (Insect)"},{"Panchu (Lint)","Manjal (Yellow)"},{"Thaitu (Plate)","Paitu (Plush)"},{"Kan (Eye)","Vandu (Beetle)"},{"Thatha (GrandFather)","Nathai (Snail)"},{"Panthu (Ball)","Aanthai (Owl)"},{"Uppu (Salt)","Srappu (Special)"},{"Paatham (Foot)","Paambu (Snake)"},{"Naai (Dog)","Kaai (Vegtables)"},{"Ther (Chariot)","Malar (Flower)"},{"Paal (Paal)","Pally (Lizard)"},{"Evvaru (How)","Sevvay (Tuesday)"},{"Thamizh (Tamil)","Yaizh (RPG)"},{"Thel (Scorpion)","Palli (School)"},{"PayirChi (Practice)","Ciṟpam (Sculpture)"},{"Meen (Fish)","Nanri (Thanks)"}};

    private final static String[][] uyir_meyel = {{"கப்பல்","கருப்பு"},{"இங்ஙனம்","எங்ஙனம்"},{"சட்டை","சங்கம்"},{"கவிஞர்","கலைஞர்"},{"கடல்","படம்"},{"பணம்","வண்ணம்"},{"தவறு","தலை"},{"நடனம்","நகை"},{"பசி","படி"},{"மரம்","மலை"},{"பயம்","ஞாயம்"},{"வரம்","வீரம்"},{"நிலம்","பாலம்"},{"வலி","வணக்கம்"},{"பழம்","கழகம்"},{"தாளம்","பள்ளம்"},{"அறன்","இறகு"},{"வானம்","மனம்"}};

    private final static String[][] uyir_meyel_en = {{"Kappal (Ship)","Karupu (Black)"},{"Ingnam (Detecting)","Ennganam (Ennganam)"},{"Sattai (Shirt)","Sangam (Society)"},{"Kavingar (Poet)","Kalaingar (Artist)"},{"Kadal (Sea)","Padam (Image)"},{"Panam (Money)","Vannam (Color)"},{"Thavaru (False)","Thalai (Head)"},{"Nadanam (Dancing)","Nagai (Jewelry)"},{"Paci (Hungry)","Padi (Step)"},{"Maram (Tree)","Malai (Mountain)"},{"Payam (Fear)","Ngayam (justice)"},{"Varam (Boon)","Veeram (Valor)"},{"Nilam (Land)","Paalam (Bridge)"},{"Vali (Pain)","Vanakam (Hello)"},{"Pazham (Fruit)","Kalzhagam (Corporation)"},{"Thaalam (Percussion)","Pallam (Crater)"},{"Aran (Aran) ","Irgu (Feather)"},{"Vaanam (Sky)","Maṉam (Mind)"}};


    private final static String[][] special_word = {{"எஃகு","அஃது"}};

    private final static String[][] special_word_en = {{"Ehku (Stainless steel)","Akthu (While That)"}};

    public final static String VOWELS = "Vowels";
    public final static String VOWELS_CONSONANTS = "VowelsConsonants";
    public final static String CONSONANTS = "Consonants";
    public final static String SPECIAL_CHAR_1 = "ஃ";
    private final static int CONSONANTS_OFFSET = 11;
    private final static int VOWELS_CONSONANTS_OFFSET = 29;

    private final static String VOWEL_ENG [] ={"a","ā","i","ī","u","ū","e","ē","ai","o","ō","au"};
    private final static String SPEC_CHAR_ENG  ="AK";
    private final static String CONSONENT_ENG [] ={"k","ṅ","c","ñ","ṭ","ṇ","t","n","p","m","y","r","l","v","ḻ","ḷ","ṟ","ṉ"};
    private final static String VC_ENG [] ={"ka","ṅa","ca","ña","ṭa","ṇa","ta","na","pa","ma","ya","ra","la","va","ḻa","ḷa","ṟa","ṉa"};

    private final static String[][] vcLettersTable =
            {{"க","கா","கி","கீ","கு","கூ","கெ","கே","கை","கொ","கோ","கௌ"},
                    {"ங","ஙா","ஙி","ஙீ","ஙு","ஙூ","ஙெ","ஙே","ஙை","ஙொ","ஙோ","ஙௌ"},
                    {"ச","சா","சி","சீ","சு","சூ","செ","சே","சை","சொ","சோ","சௌ"},
                    {"ஞ","ஞா","ஞி","ஞீ","ஞு","ஞூ","ஞெ","ஞே","ஞை","ஞொ","ஞோ","ஞௌ"},
                    {"ட","டா","டி","டீ","டு","டூ","டெ","டே","டை","டொ","டோ","டௌ"},
                    {"ண","ணா","ணி","ணீ","ணு","ணூ","ணெ","ணே","ணை","ணொ","ணோ","ணௌ"},
                    {"த","தா","தி","தீ","து","தூ","தெ","தே","தை","தொ","தோ","தௌ"},
                    {"ந","நா","நி","நீ","நு","நூ","நெ","நே","நை","நொ","நோ","நௌ"},
                    {"ப","பா","பி","பீ","பு","பூ","பெ","பே","பை","பொ","போ","பௌ"},
                    {"ம","மா","மி","மீ","மு","மூ","மெ","மே","மை","மொ","மோ","மௌ"},
                    {"ய","யா","யி","யீ","யு","யூ","யெ","யே","யை","யொ","யோ","யௌ"},
                    {"ர","ரா","ரி","ரீ","ரு","ரூ","ரெ","ரே","ரை","ரொ","ரோ","ரௌ"},
                    {"ல","லா","லி","லீ","லு","லூ","லெ","லே","லை","லொ","லோ","லௌ"},
                    {"வ","வா","வி","வீ","வு","வூ","வெ","வே","வை","வொ","வோ","வௌ"},
                    {"ழ","ழா","ழி","ழீ","ழு","ழூ","ழெ","ழே","ழை","ழொ","ழோ","ழௌ"},
                    {"ள","ளா","ளி","ளீ","ளு","ளூ","ளெ","ளே","ளை","ளொ","ளோ","ளௌ"},
                    {"ற","றா","றி","றீ","று","றூ","றெ","றே","றை","றொ","றோ","றௌ"},
                    {"ன","னா","னி","னீ","னு","னூ","னெ","னே","னை","னொ","னோ","னௌ"}};

    @Nullable
    public static String[] getWordsList(int letterIndex, String lgName) {

        if(lgName.equals(VOWELS))
             return wordsList[letterIndex];
        if(lgName.equals(CONSONANTS))
           return meyeluthu[letterIndex];
        if(lgName.equals(VOWELS_CONSONANTS))
            return uyir_meyel[letterIndex];
        if(lgName.equals(SPECIAL_CHAR_1))
            return special_word[letterIndex];
        return null;
    }

    public static String[] getEnglishWordsList(int letterIndex, String lgName) {

        if(lgName.equals(VOWELS))
            return wordsList_en[letterIndex];
        if(lgName.equals(CONSONANTS))
            return meyeluthu_en[letterIndex];
        if(lgName.equals(VOWELS_CONSONANTS))
            return uyir_meyel_en[letterIndex];
        if(lgName.equals(SPECIAL_CHAR_1))
            return special_word_en[letterIndex];
        return null;
    }

    /**
     * Return the result of given Vowel + Consonant Letters
     * @param vowelIndex
     * @param consonantIndex

     * @return
     */

    public static String getVowelsConsonantsLetter(int vowelIndex,int consonantIndex){
        return vcLettersTable[consonantIndex][vowelIndex];
    }

    public static List<String> getVowelsList(Context context){
        return Arrays.asList(context.getResources().getStringArray(R.array.v_alphabets));
    }

    public static String getLetterGroupName(Context context,String letter){
        if(letter.equals(SPECIAL_CHAR_1)) return SPECIAL_CHAR_1;
        List list = Arrays.asList(context.getResources().getStringArray(R.array.v_alphabets));
        if(list.contains(letter)) return VOWELS;
        list = Arrays.asList(context.getResources().getStringArray(R.array.c_alphabets));
        if(list.contains(letter)) return CONSONANTS;
        return VOWELS_CONSONANTS;
    }

    public static List<String> getConsonantsList(Context context){
        return Arrays.asList(context.getResources().getStringArray(R.array.c_alphabets));
    }
    public static List<String> getVowelsConsonantsLetter(Context context){
        return Arrays.asList(context.getResources().getStringArray(R.array.c_v_alphabets));
    }

    public static String getLetter(int letterIndex,String lgName){
        if(lgName.equals(VOWELS))
            return VOWEL_ENG[letterIndex];
        if(lgName.equals(CONSONANTS))
            return CONSONENT_ENG[letterIndex];
        if(lgName.equals(VOWELS_CONSONANTS))
            return VC_ENG[letterIndex];
        return SPEC_CHAR_ENG;
    }


    public static String getTamilTitle(String lgName,Context context){
        if(lgName.equals(VOWELS))
            return (context.getResources().getString(R.string.menu1));
        if(lgName.equals(CONSONANTS))
            return (context.getResources().getString(R.string.menu2));
        if(lgName.equals(VOWELS_CONSONANTS))
            return (context.getResources().getString(R.string.menu3));
         return (context.getResources().getString(R.string.menu4));
    }

}
