package com.example.themarblegame.main;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import com.example.themarblegame.R;
import com.example.themarblegame.interfaces.GameDesigner;
import com.example.themarblegame.managers.ObjectManager;
import com.example.themarblegame.managers.PortraitManager;
import com.example.themarblegame.obstacles.Portrait;

public class HomeInterface extends PlayerBank implements GameDesigner {
    private static final String TAG = "msg";
    private static DrawText drawText = new DrawText();
    private final SkinBank skinBank = new SkinBank();
    private final ObjectManager objectManager = new ObjectManager();
    public static PhysicsTimer homePhysicsTimer = new PhysicsTimer();

    public static float POSITION = 0;
    public static float SCROLL = 0;
    public static boolean isReCentered = true;
    public static boolean ignoreAutoScroll = false;

    //EXIT BUTTON / PRICE POP UP / CONFIRM AND CANCEL / shop HUD
    public final Physics exitMenuPhysics = new Physics();
    public final Physics darkBackgroundPhysics = new Physics();

    //MAIL MENU PHYSICS
    public static int mailMenuState = 0;
    private final Physics[] mailPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //MAIL SLOT 1 MENU PHYSICS
    public static int mailSlot1State = 0;
    private final Physics[] mailSlot1Physics = {
            new Physics(),
            new Physics(),
            new Physics()
    };

    //MAIL SLOT 2 MENU PHYSICS
    public static int mailSlot2State = 0;
    private final Physics[] mailSlot2Physics = {
            new Physics(),
            new Physics(),
            new Physics()
    };

    //MAIL SLOT 3 MENU PHYSICS
    public static int mailSlot3State = 0;
    private final Physics[] mailSlot3Physics = {
            new Physics(),
            new Physics(),
            new Physics()
    };

    //MAIL SLOT 4 MENU PHYSICS
    public static int mailSlot4State = 0;
    private final Physics[] mailSlot4Physics = {
            new Physics(),
            new Physics(),
            new Physics()
    };

    //SETTINGS MENU PHYSICS
    public static int settingsMenuState = 0;
    private final Physics[] settingsPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
    };

    //SETTINGS HOW TO PLAY
    public static int settingsHowToPlayState = 0;
    private final Physics[] settingsHowToPhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
    };

    //SETTINGS LANGUAGE
    public static int settingsLanguageState = 0;
    private final Physics[] settingsLanguagePhysics = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
    };

    //LIVES LEFT MENU PHYSICS
    public static int livesMenuState = 0;
    private final Physics[] livesPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //COINS MENU PHYSICS
    public static int coinsMenuState = 0;
    private final Physics[] coinsPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    // SHOP MENU (BASED)
    public static int shopMenuState = 0;
    private final Physics[] shopPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    // SHOP SKIN MENU (SUB)
    public static int shopSkinsMenuState = 0;
    private final Physics[] shopSkinsPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics()
    };

    //MY STUFF MENU PHYSICS
    public static int myStuffMenuState = 0;
    private final Physics[] myStuffPhysicsList = {
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
            new Physics(),
    };

    //LOADING SCREEN PHYSICS

    private static boolean isLoading1 = false;
    private static boolean isLoading2 = false;
    public static int loadingScreen1State = 0;
    public static int loadingScreen2State = 0;
    private final Physics[] loading1Physics = {
            new Physics(),
            new Physics(),
            new Physics()
    };
    //this is the loading bar
    private final Physics[] loading2Physics = {
            new Physics()
    };

    //INITIAL PLAY MENU
    public static boolean isInPlayState = true;
    public static int playState = 0;
    private final Physics[] playPhysics = {
            new Physics(),
            new Physics(),
            new Physics()
    };

    //MAIN MENUS BOOLEANS
    private static boolean shopSelected = false;
    private static boolean myStuffSelected = false;
    private static boolean coinsSelected = false;
    private static boolean livesSelected = false;
    private static boolean isOutOfLives = false;
    private static boolean settingsSelected = false;
    private static boolean mailSelected = false;
    //SUBMENUS BOOLEANS
    private static boolean shopSkinsSelected = false;
    private static boolean mailSlot1Selected = false;
    private static boolean mailSlot2Selected = false;
    private static boolean mailSlot3Selected = false;
    private static boolean mailSlot4Selected = false;
    private static boolean settingsHowToPlaySelected = false; // add to array later
    private static boolean settingsLanguageSelected = false; //add to array later

    private static final boolean[] anyMenu = new boolean[15];

    //Background clouds (always active)
//    public final Portrait cloud1 = new Portrait(Constants.SCREEN_WIDTH, Scale.GROUND-Scale.UNIT*30, Constants.SCREEN_WIDTH + Scale.UNIT * 7, Scale.GROUND-Scale.UNIT*26, R.drawable.cloud1, false);
//    public final Portrait cloud2 = new Portrait(Constants.SCREEN_WIDTH, Scale.GROUND-Scale.UNIT*32, Constants.SCREEN_WIDTH + Scale.UNIT * 7, Scale.GROUND-Scale.UNIT*28, R.drawable.cloud2, false);
//    public final Portrait cloud3 = new Portrait(Constants.SCREEN_WIDTH, Scale.GROUND-Scale.UNIT*28, Constants.SCREEN_WIDTH + Scale.UNIT * 4, Scale.GROUND-Scale.UNIT*24, R.drawable.cloud3, false);

    //DIM RECT for button pressed
    public static final Rect longPointer = new Rect();
    public static final Rect shortPointer = new Rect();
    public static final Rect levelShortPointer = new Rect();

    //LOADING SCREEN
    private static final Rect loadingScreen = new Rect(-Constants.SCREEN_WIDTH-Scale.UNIT, 0, 0, Constants.SCREEN_HEIGHT);
    private static final Rect loadingScreenHint = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/3, 0, Constants.SCREEN_HEIGHT/3 + Scale.UNIT*2);
    private static Rect loadingScreenBar = new Rect(-Scale.UNIT*2, Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3, 0, (int) (Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3.25));

    //PLAY MENU
    private static final Rect playMenu = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    private static final Rect playHeader = new Rect(Scale.UNIT*3, Constants.SCREEN_HEIGHT/6 + Scale.UNIT, Scale.UNIT*11, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5);
    private static final Rect playButton = new Rect (Scale.UNIT*5, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*6, Scale.UNIT*9, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*8);

    //EXIT MENU and dark background
    private static final Portrait exitMenu = new Portrait(-Scale.UNIT, Constants.SCREEN_HEIGHT/6 - (Scale.UNIT/2), 0, Constants.SCREEN_HEIGHT/6 + (Scale.UNIT/2), R.drawable.exit_menu, false);
    private static final Rect darkBackground = new Rect(-Constants.SCREEN_WIDTH, 0, 0, Constants.SCREEN_HEIGHT);

    //merge with shop
    private static final Rect shopConfirmMenu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*14, Constants.SCREEN_WIDTH + Scale.UNIT*11, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*16.5));
    private static final Rect shopConfirm = new Rect(Constants.SCREEN_WIDTH, (int) (Constants.SCREEN_HEIGHT/6 + Scale.UNIT*14.25), (int) (Constants.SCREEN_WIDTH + Scale.UNIT*3.75), (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*16.25));
    private static final Rect shopCancel = new Rect(Constants.SCREEN_WIDTH, (int) (Constants.SCREEN_HEIGHT/6 + Scale.UNIT*14.25), (int) (Constants.SCREEN_WIDTH + Scale.UNIT*3.75), (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*16.25));
    private static final Rect shopPricePopUp = new Rect(-Scale.UNIT*4, (int) (Scale.UNIT*1.35), 0, (int) (Scale.UNIT*2.25));

    //HOME HEADER BUTTONS (except for main header)
    private static final Portrait mainHeader = new Portrait(0, 0, Constants.SCREEN_WIDTH, (int) (Scale.UNIT*1.75), R.drawable.header_icon, false);
    private static final Portrait livesLeftIcon = new Portrait((int) (Scale.UNIT*6.15), 0, Scale.UNIT*8, (int) (Scale.UNIT*1.75), R.drawable.lives_icon, false);
    private static final Rect livesLeftTimer = new Rect((int) (Scale.UNIT*7.075),(int) (Scale.UNIT*.35), (int) (Scale.UNIT*9.5), (int) (Scale.UNIT*1.45));
    private static final Portrait mailIcon = new Portrait(Scale.UNIT*10, 0, (int) (Scale.UNIT*11.75), (int) (Scale.UNIT*1.75), R.drawable.mail_icon, false);
    private static final Portrait settingsIcon = new Portrait((int) (Scale.UNIT*11.75), 0, (int) (Scale.UNIT*13.5), (int) (Scale.UNIT*1.75), R.drawable.settings_icon, false);
    private static final Portrait coinsIcon1 = new Portrait((int) (Scale.UNIT*.5), 0, (int) (Scale.UNIT*2.25), (int) (Scale.UNIT*1.75), R.drawable.coin_icon1, false);
    private static final Rect coinsIcon2 = new Rect((int) (Scale.UNIT*1.15),(int) (Scale.UNIT*.35), (Scale.UNIT*6), (int) (Scale.UNIT*1.45));

    //HOME FOOTER BUTTONS
    private static final Rect shop = new Rect(0, (int) (Constants.SCREEN_HEIGHT - (2.15 * Scale.UNIT)), (int) ((int) Scale.UNIT*4.5), Constants.SCREEN_HEIGHT);
    private static final Rect reCenter = new Rect((int) (Scale.UNIT*4.75), (int) (Constants.SCREEN_HEIGHT - (2.15 * Scale.UNIT)), (int) (Scale.UNIT*9.25), Constants.SCREEN_HEIGHT);
    private static final Rect myStuff = new Rect((int) (Scale.UNIT*9.5), (int) (Constants.SCREEN_HEIGHT - (2.15 * Scale.UNIT)), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    //MAIL MENU AND BUTTONS
    private static final Rect mailMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect mailTitleCard = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6 - (Scale.UNIT*2), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);
    private static final Rect mailSlot1 = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect mailSlot2 = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect mailSlot3 = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*8, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
    private static final Rect mailSlot4 = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //MAIL SLOT 1
    private static final Rect mailSlot1Menu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6, Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect mailSlot1TextBox = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
    private static final Rect mailSlotBackButton = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //MAIL SLOT 2
    private static final Rect mailSlot2Menu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6, Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect mailSlot2TextBox = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
//    private static final Rect mailSlot2BackButton = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //MAIL SLOT 3
    private static final Rect mailSlot3Menu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6, Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect mailSlot3TextBox = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
//    private static final Rect mailSlot3BackButton = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //MAIL SLOT 4
    private static final Rect mailSlot4Menu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6, Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect mailSlot4TextBox = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
//    private static final Rect mailSlot4BackButton = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //SETTINGS MENU AND BUTTONS
    private static final Rect settingsMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect settingsTitleCard = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6 - Scale.UNIT*2, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);
    private static final Rect settingsGMV = new Rect(-Scale.UNIT*4, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect settingsMinGMV = new Rect((int) (-Scale.UNIT*1.5), Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect settingsActualGMV = new Rect((int) (-Scale.UNIT*1.5), Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect settingsMaxGMV = new Rect((int) (-Scale.UNIT*1.5), Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect settingsSFX = new Rect(-Scale.UNIT*4, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect settingsMinSFX = new Rect((int) (-Scale.UNIT*1.5), Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect settingsActualSFX = new Rect((int) (-Scale.UNIT*1.5), Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect settingsMaxSFX = new Rect((int) (-Scale.UNIT*1.5), Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect settingsHowToPlay = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*8, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
//    private static final Rect settingsChangeShadowColor = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*8, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
    private static final Rect settingsLanguage = new Rect(-Scale.UNIT*10, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //SETTINGS HOW TO PLAY
    private static final Rect settingsHowToPlayMenu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6, Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect settingsHowToPlayDisplay = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
    private static final Rect settingsBackButton = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //SETTINGS LANGUAGE
    private static final Rect settingsLanguageMenu = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6, Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect settingsLanguageEnglish = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, (int) (Constants.SCREEN_WIDTH + Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect settingsLanguageChinese = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*2, (int) (Constants.SCREEN_WIDTH + Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*4);
    private static final Rect settingsLanguageGerman = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, (int) (Constants.SCREEN_WIDTH + Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect settingsLanguageJapanese = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*5, (int) (Constants.SCREEN_WIDTH + Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*7);
    private static final Rect settingsLanguageKorean = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*8, (int) (Constants.SCREEN_WIDTH + Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
    private static final Rect settingsLanguageSpanish = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*8, (int) (Constants.SCREEN_WIDTH + Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
//    private static final Rect settingsLanguageBackButton = new Rect(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/6 + Scale.UNIT*11, (Constants.SCREEN_WIDTH + Scale.UNIT*10), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*13);

    //LIVES LEFT MENU AND BUTTONS
    private static final Rect livesMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect livesDisplayHeader = new Rect((-Scale.UNIT*10), (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT), 0, (int) ((Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10.5));
    private static final Rect livesConfirmShowAd = new Rect((int) (-Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11), 0, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*13));
    private static final Rect livesCancelShowAd = new Rect((int) (-Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11), 0, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*13));
    private static final Rect livesOutOfLives = new Rect(-Scale.UNIT*5, (int) (Constants.SCREEN_HEIGHT/6 + Scale.UNIT/2), 0, Constants.SCREEN_HEIGHT/6 - Scale.UNIT);

    //COINS MENU AND BUTTONS
    private static final Rect coinsMenu = new Rect(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*14);
    private static final Rect coinsDisplayHeader = new Rect((-Scale.UNIT*10), (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT/2), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT*10);
    private static final Rect coinsConfirmShowAd = new Rect((int) (-Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11), 0, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*13));
    private static final Rect coinsCancelShowAd = new Rect((int) (-Scale.UNIT*4.75), (Constants.SCREEN_HEIGHT/6) + (int)(Scale.UNIT*11), 0, (Constants.SCREEN_HEIGHT/6) + (Scale.UNIT*13));

    //SHOP MAIN MENU and BUTTONS
    private static final Portrait shopMenu = new Portrait(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14, R.drawable.menu2, true);
    private static final Rect shopTitleCard = new Rect(-Scale.UNIT*6, (Constants.SCREEN_HEIGHT/6) - (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);
    private static final Rect shopHud1 = new Rect(-Scale.UNIT, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (3*Scale.UNIT));
    private static final Rect shopHud2 = new Rect(-Scale.UNIT, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (6*Scale.UNIT));
    private static final Rect shopHud3 = new Rect(-Scale.UNIT, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (9*Scale.UNIT));
    private static final Rect shopBalloonsDisplay = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect shopBalloonsPriceTag = new Rect((-Scale.UNIT*5), (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect shopBalloonsBuy = new Rect((int) (-Scale.UNIT*2.5), (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect shopRainDisplay = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect shopRainPriceTag = new Rect(-Scale.UNIT*5, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect shopRainBuy = new Rect((int) (-Scale.UNIT*2.5), (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect shopShellShockDisplay = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect shopShellShockPriceTag = new Rect(-Scale.UNIT*5, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect shopShellShockBuy = new Rect((int) (-Scale.UNIT*2.5), (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect shopSkins = new Rect(-Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (11*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (13*Scale.UNIT));

    //SHOP SUBMENUS and BUTTONS
    private static final Rect shopSkinsTitleCard = new Rect(Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/6)- (Scale.UNIT/2), (int) (Constants.SCREEN_WIDTH + Scale.UNIT*3.75), (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);
    private static final Portrait shopSkinsSubMenu = new Portrait(Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/6), Constants.SCREEN_WIDTH + Scale.UNIT*11, (Constants.SCREEN_HEIGHT/6) + (14*Scale.UNIT), R.drawable.menu3, false);
    private static final Portrait shopSkinItem1Display = new Portrait(Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT), R.drawable.skin_b1, false);
    private static final Rect shopSkinItem1PriceTag = new Rect((int) (Constants.SCREEN_WIDTH + Scale.UNIT * 2.25), (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), (int) (Constants.SCREEN_WIDTH + Scale.UNIT*7.25), (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect shopSkinItem1Buy = new Rect((int) (Constants.SCREEN_WIDTH + Scale.UNIT * 7.5), (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect shopSkinItem2Display = new Rect(Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect shopSkinItem2PriceTag = new Rect((int) (Constants.SCREEN_WIDTH * 2.25), (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), (int) (Constants.SCREEN_WIDTH + Scale.UNIT*7.25), (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect shopSkinItem2Buy = new Rect((int) (Constants.SCREEN_WIDTH * 7.5), (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect shopSkinItem3Display = new Rect(Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect shopSkinItem3PriceTag = new Rect((int) (Constants.SCREEN_WIDTH * 2.25), (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), (int) (Constants.SCREEN_WIDTH + Scale.UNIT*7.25), (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect shopSkinItem3Buy = new Rect((int) (Constants.SCREEN_WIDTH * 7.5), (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect shopSkinsBackButton = new Rect(Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/6) + (11*Scale.UNIT), Constants.SCREEN_WIDTH + Scale.UNIT*10, (Constants.SCREEN_HEIGHT/6) + (13*Scale.UNIT));

    //MY STUFF MENU BUTTONS
    private static final Portrait myStuffMenu = new Portrait(-Scale.UNIT*11, Constants.SCREEN_HEIGHT/6, 0, (Constants.SCREEN_HEIGHT/6)+Scale.UNIT*14, R.drawable.menu1, true);
    private static final Rect myStuffTitleCard = new Rect(-Scale.UNIT*6, (Constants.SCREEN_HEIGHT/6) - (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + Scale.UNIT);
    private static final Rect myHud1 = new Rect(-Scale.UNIT, (int) ((Constants.SCREEN_HEIGHT/6) + (11.5*Scale.UNIT)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + (12.5*Scale.UNIT)));
    private static final Rect myHud2 = new Rect(-Scale.UNIT, (int) ((Constants.SCREEN_HEIGHT/6) + (11.5*Scale.UNIT)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + (12.5*Scale.UNIT)));
    private static final Rect myHud3 = new Rect(-Scale.UNIT, (int) ((Constants.SCREEN_HEIGHT/6) + (11.5*Scale.UNIT)), 0, (int) ((Constants.SCREEN_HEIGHT/6) + (12.5*Scale.UNIT)));
    private static final Rect myBalloons = new Rect(-Scale.UNIT*3, (int) ((Constants.SCREEN_HEIGHT/6) + (11.5*Scale.UNIT)), 0, (Constants.SCREEN_HEIGHT/6) + (13*Scale.UNIT));
    private static final Rect myRain = new Rect(-Scale.UNIT*3, (int) ((Constants.SCREEN_HEIGHT/6) + (11.5*Scale.UNIT)), 0, (Constants.SCREEN_HEIGHT/6) + (13*Scale.UNIT));
    private static final Rect myShellShock = new Rect(-Scale.UNIT*3, (int) ((Constants.SCREEN_HEIGHT/6) + (11.5*Scale.UNIT)), 0, (Constants.SCREEN_HEIGHT/6) + (13*Scale.UNIT));
    private static final Rect selectedSkin = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect skinSlot1 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect skinSlot2 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect skinSlot3 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
    private static final Rect skinSlot4 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect skinSlot5 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect skinSlot6 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect skinSlot7 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));
    private static final Rect skinSlot8 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect skinSlot9 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect skinSlot10 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
    private static final Rect skinSlot11 = new Rect(-Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), 0, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));

    //LEVEL BUTTONS
    private final Rect level1Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND + Scale.UNIT, (int) (Scale.UNIT*9.25), Scale.GROUND + Scale.UNIT*3);
    private final Rect level2Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*2, (int) (Scale.UNIT*9.25), Scale.GROUND);
    private final Rect level3Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*5, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*3);
    private final Rect level4Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*8, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*6);
    private final Rect level5Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*11, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*9);
    private final Rect level6Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*14, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*12);
    private final Rect level7Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*17, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*15);
    private final Rect level8Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*20, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*18);
    private final Rect level9Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*23, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*21);
    private final Rect level10Button = new Rect((int) (Scale.UNIT*4.75), Scale.GROUND-Scale.UNIT*26, (int) (Scale.UNIT*9.25), Scale.GROUND-Scale.UNIT*24);

    public static int indicatorState = 0;

    private final Rect level2Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*1.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*.5));
    private final Rect level3Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*4.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*3.5));
    private final Rect level4Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*7.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*6.5));
    private final Rect level5Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*10.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*9.5));
    private final Rect level6Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*13.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*12.5));
    private final Rect level7Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*16.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*15.5));
    private final Rect level8Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*19.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*18.5));
    private final Rect level9Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*22.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*21.5));
    private final Rect level10Indicator = new Rect((Scale.UNIT*2), (int)(Scale.GROUND - Scale.UNIT*25.5), (int) (Scale.UNIT*4.5), (int)(Scale.GROUND - Scale.UNIT*24.5));


    public void drawIndicator(Canvas canvas) {
        switch (indicatorState) {
            case 2:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level2Indicator, "Locked");
                break;
            case 3:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level3Indicator, "Locked");
                break;
            case 4:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level4Indicator, "Locked");
                break;
            case 5:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level5Indicator, "Locked");
                break;
            case 6:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level6Indicator, "Locked");
                break;
            case 7:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level7Indicator, "Locked");
                break;
            case 8:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level8Indicator, "Locked");
                break;
            case 9:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level9Indicator, "Locked");
                break;
            case 10:
                drawText.drawInCenterOfRect(canvas, textPaint, rect, level10Indicator, "Locked");
                break;
        }
    }

    /*
    LOADING SCREEN (VERY IMPORTANT FOR SMOOTH TRANSITIONS).
    Have some boolean prevent sceneManager gameView from changing,
    until after loadScreen travel animation is finished.
    This will require changes in HomeTouchControls.
    Use same model as other menu up functions.
    nest this inside drawOnLevelMenus and updateOnLevelMenus.
     */

    //PLAY BUTTON GETTER
    public static Rect getPlayButton() {return playButton;}

    //LOADING SCREEN GETTER
    public static Rect getLoadingScreen() {return loadingScreen;}
    public static boolean isIsLoading1() {return isLoading1;}

    //FOOTER BUTTON GETTERS
    public static Rect getShop() {
        return shop;
    }
    public static Rect getReCenter() {
        return reCenter;
    }
    public static Rect getMyStuff() {
        return myStuff;
    }

    //HEADER BUTTON GETTERS (and also exit menu)
    public static Rect getMainHeader() {return mainHeader.getRect();}
    public static Rect getCoinsIcon2() {return coinsIcon2;}
    public static Rect getCoinsIcon1() {return coinsIcon1.getRect();}
    public static Rect getLivesLeftIcon() {return livesLeftIcon.getRect();}
    public static Rect getLivesLeftTimer() {return livesLeftTimer;}
    public static Rect getMailIcon() {return mailIcon.getRect();}
    public static Rect getSettingsIcon() {return settingsIcon.getRect();}
    public static Rect getExitMenu() {return exitMenu.getRect();}

    //SHOP MENU BUTTON GETTER
    public static Rect getShopSkins() {
        return shopSkins;
    }
    public static Rect getShopSkinsBackButton() {return shopSkinsBackButton;}
    public static Rect getShopBalloonsBuy() {return shopBalloonsBuy;}
    public static Rect getShopConfirm() {return shopConfirm;}
    public static Rect getShopCancel() {return shopCancel;}
    public static Rect getShopRainBuy() {return shopRainBuy;}
    public static Rect getShopShellShockBuy() {return shopShellShockBuy;}
    public static Rect getShopSkinItem1Buy() {return shopSkinItem1Buy;}

    //MY STUFF GETTERS
    public static Rect getSkinSlot1() {return skinSlot1;}

    //MAIL MENU BUTTON GETTERS
    public static Rect getMailSlot1() {return mailSlot1;}
    public static Rect getMailSlotBackButton() {return mailSlotBackButton;}
    public static Rect getMailSlot2() {return mailSlot2;}
//    public static Rect getMailSlot2BackButton() {return mailSlot2BackButton;}
    public static Rect getMailSlot3() {return mailSlot3;}
//    public static Rect getMailSlot3BackButton() {return mailSlot3BackButton;}
    public static Rect getMailSlot4() {return mailSlot4;}
//    public static Rect getMailSlot4BackButton() {return mailSlot4BackButton;}

    //SETTINGS MENU BUTTON GETTERS
    public static Rect getSettingsHowToPlay() {return settingsHowToPlay;}
    public static Rect getSettingsBackButton() {return settingsBackButton;}
    public static Rect getSettingsLanguage() {return settingsLanguage;}
//    public static Rect getSettingsLanguageBackButton() {return  settingsLanguageBackButton;}
    public static Rect getSettingsLanguageEnglish() {return settingsLanguageEnglish;}
    public static Rect getSettingsLanguageChinese() {return settingsLanguageChinese;}
    public static Rect getSettingsLanguageGerman() {return settingsLanguageGerman;}
    public static Rect getSettingsLanguageJapanese() {return settingsLanguageJapanese;}
    public static Rect getSettingsLanguageKorean() {return settingsLanguageKorean;}
    public static Rect getSettingsLanguageSpanish() {return settingsLanguageSpanish;}

    //LIVES MENU GETTER
    public static Rect getLivesConfirmShowAd() {return livesConfirmShowAd;}
    public static Rect getLivesCancelShowAd() {return livesCancelShowAd;}

    //COINS MENU GETTER
    public static Rect getCoinsConfirmShowAd() {return coinsConfirmShowAd;}
    public static Rect getCoinsCancelShowAd() {return coinsCancelShowAd;}

    //BOOLEAN GETTERS AND SETTERS
    public static void setIsInPlayState(boolean isInPlayState) {
        HomeInterface.isInPlayState = isInPlayState;
    }
    public static boolean getIsOutOfLives() {return isOutOfLives;}
    public static void setIsOutOfLives(boolean isOutOfLives) {
        HomeInterface.isOutOfLives = isOutOfLives;
    }
    public static void setIsLoading1(boolean isLoading1) {
        HomeInterface.isLoading1 = isLoading1;
    }
    public static void setSettingsHowToPlaySelected(boolean settingsHowToPlaySelected) {
        HomeInterface.settingsHowToPlaySelected = settingsHowToPlaySelected;
    }
    public static void setSettingsLanguageSelected(boolean settingsLanguageSelected) {
        HomeInterface.settingsLanguageSelected = settingsLanguageSelected;
    }
    public static void setMailSlot1Selected(boolean mailSlot1Selected) {
        HomeInterface.mailSlot1Selected = mailSlot1Selected;}
    public static void setMailSlot2Selected(boolean mailSlot2Selected) {
        HomeInterface.mailSlot2Selected = mailSlot2Selected;}
    public static void setMailSlot3Selected(boolean mailSlot3Selected) {
        HomeInterface.mailSlot3Selected = mailSlot3Selected;}
    public static void setMailSlot4Selected(boolean mailSlot4Selected) {
        HomeInterface.mailSlot4Selected = mailSlot4Selected;}
    public static void setShopSelected(boolean shopSelected) {
        HomeInterface.shopSelected = shopSelected;
    }
    public static boolean isMailSelected() {return mailSelected;}
    public static void setMailSelected(boolean mailSelected) {
        HomeInterface.mailSelected = mailSelected;}
    public static void setShopSkinsSelected(boolean shopSkinsSelected) {
        HomeInterface.shopSkinsSelected = shopSkinsSelected;}
    public static boolean isShopSelected() {
        return shopSelected;
    }
    public static boolean isMyStuffSelected() {
        return myStuffSelected;
    }
    public static void setMyStuffSelected(boolean myStuffSelected) {
        HomeInterface.myStuffSelected = myStuffSelected;
    }
    public static DrawText getDrawText() {
        return drawText;
    }
    public static void setDrawText(DrawText drawText) {
        HomeInterface.drawText = drawText;
    }
    public static boolean isCoinsSelected() {
        return coinsSelected;
    }
    public static void setCoinsSelected(boolean coinsSelected) {
        HomeInterface.coinsSelected = coinsSelected;
    }
    public static boolean isLivesSelected() {
        return livesSelected;
    }
    public static void setLivesSelected(boolean livesSelected) {
        HomeInterface.livesSelected = livesSelected;
    }
    public static boolean isSettingsSelected() {
        return settingsSelected;
    }
    public static void setSettingsSelected(boolean settingsSelected) {
        HomeInterface.settingsSelected = settingsSelected;
    }

    //THIS IS FOR ACTIVATING DARK BACKGROUND WHENEVER ANY MENU IS ACTIVE
    public static boolean isAnyMenu() {
        anyMenu[0] = shopSelected;
        anyMenu[1] = myStuffSelected;
        anyMenu[2] = coinsSelected;
        anyMenu[3] = livesSelected;
        anyMenu[4] = settingsSelected;
        anyMenu[5] = mailSelected;
        anyMenu[6] = shopSkinsSelected;
        anyMenu[7] = mailSlot1Selected;
        anyMenu[8] = mailSlot2Selected;
        anyMenu[9] = mailSlot3Selected;
        anyMenu[10] = mailSlot4Selected;
        anyMenu[11] = settingsHowToPlaySelected;
        anyMenu[12] = settingsLanguageSelected;
        anyMenu[13] = LevelInterface.isStartMenu;
        anyMenu[14] = LevelInterface.isAreYouSure;

        for (boolean b : anyMenu) {
            if (b)
                return true;
        }
        return false;
    }

    //LEVEL BUTTON GETTERS
    public Rect getLevel1Button() {
        return level1Button;
    }
    public Rect getLevel2Button() {
        return level2Button;
    }
    public Rect getLevel3Button() {
        return level3Button;
    }
    public Rect getLevel4Button() {
        return level4Button;
    }
    public Rect getLevel5Button() {
        return level5Button;
    }
    public Rect getLevel6Button() {
        return level6Button;
    }
    public Rect getLevel7Button() {
        return level7Button;
    }
    public Rect getLevel8Button() {
        return level8Button;
    }
    public Rect getLevel9Button() {
        return level9Button;
    }
    public Rect getLevel10Button() {
        return level10Button;
    }

    Rect rect = new Rect();
    Paint dynamicPaint = new Paint();
    Paint transparentPaint = new Paint();

    public void drawLevels(Canvas canvas) {
//        dynamicPaint.setColor(Color.CYAN);
//        transparentPaint.setAntiAlias(true);
//        transparentPaint.setColor(R.color.transparentColor);

        //levels will go first in order to keep them in background
        canvas.translate(0, HomeTouchControls.SCROLL);
//        canvas.drawBitmap(cloud1.getScaledBitmap(), null, cloud1.getRect(), null);
//        canvas.drawBitmap(cloud2.getScaledBitmap(), null, cloud2.getRect(), null);
//        canvas.drawBitmap(cloud3.getScaledBitmap(), null, cloud3.getRect(), null);

        PlayerProgression.drawProgress(canvas);

        canvas.drawRect(level1Button, dynamicPaint);
        canvas.drawRect(level2Button, dynamicPaint);
        canvas.drawRect(level3Button, dynamicPaint);
        canvas.drawRect(level4Button, dynamicPaint);
        canvas.drawRect(level5Button, dynamicPaint);
        canvas.drawRect(level6Button, dynamicPaint);
        canvas.drawRect(level7Button, dynamicPaint);
        canvas.drawRect(level8Button, dynamicPaint);
        canvas.drawRect(level9Button, dynamicPaint);
        canvas.drawRect(level10Button, dynamicPaint);
        canvas.drawRect(levelShortPointer, transparentPaint);

        //level text; will delete later once I implement images
        dynamicPaint.setColor(Color.BLACK);
        dynamicPaint.setTextSize(Constants.SCREEN_WIDTH/25f);
        dynamicPaint.setTextAlign(Paint.Align.LEFT);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 1", rect, ((Constants.SCREEN_HEIGHT - (4 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 2", rect, ((Constants.SCREEN_HEIGHT - (7 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 3", rect, ((Constants.SCREEN_HEIGHT - (10 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 4", rect, ((Constants.SCREEN_HEIGHT - (13 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 5", rect, ((Constants.SCREEN_HEIGHT - (16 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 6", rect, ((Constants.SCREEN_HEIGHT - (19 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 7", rect, ((Constants.SCREEN_HEIGHT - (22 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 8", rect, ((Constants.SCREEN_HEIGHT - (25 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 9", rect, ((Constants.SCREEN_HEIGHT - (28 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, dynamicPaint, "Level 10", rect, ((Constants.SCREEN_HEIGHT - (31 * Scale.UNIT)) - Scale.UNIT/1.15f), 2f);

        drawIndicator(canvas);

        canvas.translate(0, -HomeTouchControls.SCROLL);

//        canvas.drawRect(darkBackground, transparentPaint);
    }

    Paint mainMenuPaint = new Paint();
    Paint menuButtonPaint = new Paint();
    Paint subMenuPaint = new Paint();
    Paint subMenuButtonPaint = new Paint();
    Paint exitMenuPaint = new Paint();
    Paint textPaint = new Paint();
    Paint headerTextPaint = new Paint();
    Paint subHeaderTextPaint = new Paint();
    Paint popUpPaint = new Paint();

    //THESE FUNCTIONS ARE DESIGNED FOR REORDERING PURPOSES WHEN SWITCHING BETWEEN GAME VIEW AND  HOME VIEW
    public void drawExitMenu(Canvas canvas) {
        canvas.drawBitmap(exitMenu.getScaledBitmap(), null, exitMenu.getRect(), null);
    }
    public void drawDarkBackground(Canvas canvas) {
        canvas.drawRect(darkBackground, transparentPaint);

    }
    public void drawLoadingScreen(Canvas canvas) {
        canvas.drawRect(loadingScreen, popUpPaint);
        canvas.drawRect(loadingScreenHint, menuButtonPaint);
        canvas.drawRect(loadingScreenBar, menuButtonPaint);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, loadingScreenHint, "Hint...");

        canvas.drawRect(playMenu, popUpPaint);
        canvas.drawRect(playButton, menuButtonPaint);
        canvas.drawRect(playHeader, menuButtonPaint);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, playButton, "Play");

        //this is here because dim needs to be above everything
        canvas.drawRect(longPointer, transparentPaint);
        canvas.drawRect(shortPointer, transparentPaint);
    }



    //ALL MENUS THAT ARE ALSO AVAILABLE FOR GAME VIEW
    public void drawOnLevelMenus(Canvas canvas) {
        exitMenuPaint.setColor(Color.RED);
        subMenuButtonPaint.setColor(Color.LTGRAY);
        subMenuPaint.setColor(Color.BLUE);
        menuButtonPaint.setColor(Color.YELLOW);
        mainMenuPaint.setColor(Color.GREEN);
        popUpPaint.setColor(Color.LTGRAY);

        canvas.drawRect(coinsMenu, mainMenuPaint);
        canvas.drawRect(livesMenu, mainMenuPaint);
        canvas.drawRect(settingsMenu, mainMenuPaint);
        canvas.drawRect(settingsHowToPlayMenu, mainMenuPaint);
        canvas.drawRect(settingsLanguageMenu, mainMenuPaint);
        canvas.drawRect(mailMenu, mainMenuPaint);
        canvas.drawRect(mailSlot1Menu, mainMenuPaint);
        canvas.drawRect(mailSlot2Menu, mainMenuPaint);
        canvas.drawRect(mailSlot3Menu, mainMenuPaint);
        canvas.drawRect(mailSlot4Menu, mainMenuPaint);

        // coins menu and buttons
        canvas.drawRect(coinsDisplayHeader, menuButtonPaint);
        canvas.drawRect(coinsConfirmShowAd, menuButtonPaint);
        canvas.drawRect(coinsCancelShowAd, menuButtonPaint);

        // lives left menu and buttons
        canvas.drawRect(livesOutOfLives, menuButtonPaint);
        canvas.drawRect(livesDisplayHeader, menuButtonPaint);
        canvas.drawRect(livesConfirmShowAd, menuButtonPaint);
        canvas.drawRect(livesCancelShowAd, menuButtonPaint);


        textPaint.setTextSize(Constants.SCREEN_WIDTH/25f);
        textPaint.setColor(Color.BLACK);
        headerTextPaint.setTextSize(Constants.SCREEN_WIDTH/15f);
        headerTextPaint.setColor(Color.BLACK);
        headerTextPaint.setFakeBoldText(true);
        subHeaderTextPaint.setTextSize(Constants.SCREEN_WIDTH/25f);
        subHeaderTextPaint.setColor(Color.BLACK);
        subHeaderTextPaint.setFakeBoldText(true);

        //COIN AND LIVES MENU
        drawText.drawInCenterOfRect(canvas, textPaint, rect, coinsDisplayHeader, "[watch ad for +1000 coin reward]");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, coinsConfirmShowAd, "Confirm");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, coinsCancelShowAd, "Cancel");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, livesDisplayHeader, "[watch ad for +1 life reward]");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, livesConfirmShowAd, "Confirm");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, livesCancelShowAd, "Cancel");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, livesOutOfLives, "Out of lives...");

        //-------------MAIL MENU
        // mail menu and buttons
        canvas.drawRect(mailTitleCard, menuButtonPaint);
        canvas.drawRect(mailSlot1, menuButtonPaint);
        canvas.drawRect(mailSlot2, menuButtonPaint);
        canvas.drawRect(mailSlot3, menuButtonPaint);
        canvas.drawRect(mailSlot4, menuButtonPaint);
        // all mail slots here

        canvas.drawRect(mailSlot1TextBox, menuButtonPaint);
        canvas.drawRect(mailSlotBackButton, menuButtonPaint);
        canvas.drawRect(mailSlot2TextBox, menuButtonPaint);
//        canvas.drawRect(mailSlot2BackButton, menuButtonPaint);
        canvas.drawRect(mailSlot3TextBox, menuButtonPaint);
//        canvas.drawRect(mailSlot3BackButton, menuButtonPaint);
        canvas.drawRect(mailSlot4TextBox, menuButtonPaint);
//        canvas.drawRect(mailSlot4BackButton, menuButtonPaint);
        drawText.drawInCenterOfRect(canvas, headerTextPaint, rect, mailTitleCard, "MAIL");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot1, "-Empty-");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot2, "-Empty-");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot3, "-Empty-");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot4, "-Empty-");

        //int lines and respective String lines 1-7 will be pulled from word bank.
        drawText.text(canvas, WordBank.mailSlot1Lines, mailSlot1TextBox, textPaint, WordBank.mailSlot1Line1, WordBank.mailSlot1Line2, WordBank.mailSlot1Line3, WordBank.mailSlot1Line4, WordBank.mailSlot1Line5, WordBank.mailSlot1Line6, WordBank.mailSlot1Line7);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlotBackButton, "Go back");
        drawText.text(canvas, 1, mailSlot2TextBox, textPaint, "-Empty-", null, null, null, null, null, null);
//        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot2BackButton, "Go back");
        drawText.text(canvas, 1, mailSlot3TextBox, textPaint, "-Empty-", null, null, null, null, null, null);
//        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot3BackButton, "Go back");
        drawText.text(canvas, 1, mailSlot4TextBox, textPaint, "-Empty-", null, null, null, null, null, null);
//        drawText.drawInCenterOfRect(canvas, textPaint, rect, mailSlot4BackButton, "Go back");

        //----------SETTINGS MENU
    // settings menu and buttons
        canvas.drawRect(settingsTitleCard, menuButtonPaint);
        canvas.drawRect(settingsGMV, menuButtonPaint);
        canvas.drawRect(settingsMinGMV, menuButtonPaint);
        canvas.drawRect(settingsActualGMV, menuButtonPaint);
        canvas.drawRect(settingsMaxGMV, menuButtonPaint);
        canvas.drawRect(settingsSFX, menuButtonPaint);
        canvas.drawRect(settingsMinSFX, menuButtonPaint);
        canvas.drawRect(settingsMaxSFX, menuButtonPaint);
        canvas.drawRect(settingsActualSFX, menuButtonPaint);
        canvas.drawRect(settingsHowToPlay, menuButtonPaint);
        canvas.drawRect(settingsLanguage, menuButtonPaint);
        //settings how to play
        canvas.drawRect(settingsHowToPlayDisplay, menuButtonPaint);
        canvas.drawRect(settingsBackButton, menuButtonPaint);
        //settings language
        canvas.drawRect(settingsLanguageEnglish, menuButtonPaint);
        canvas.drawRect(settingsLanguageChinese, menuButtonPaint);
        canvas.drawRect(settingsLanguageGerman, menuButtonPaint);
        canvas.drawRect(settingsLanguageJapanese, menuButtonPaint);
        canvas.drawRect(settingsLanguageKorean, menuButtonPaint);
        canvas.drawRect(settingsLanguageSpanish, menuButtonPaint);
//        canvas.drawRect(settingsLanguageBackButton, menuButtonPaint);
        drawText.drawInCenterOfRect(canvas, headerTextPaint, rect, settingsTitleCard, "SETTINGS");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguage, "Language");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsHowToPlay, "How to play");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsGMV, "GMV");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsMinGMV, "-");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsActualGMV, "Vol.");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsMaxGMV, "+");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsSFX, "SFX");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsMinSFX, "-");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsActualSFX, "Vol.");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsMaxSFX, "+");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsHowToPlayDisplay, "Just figure it out.");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsBackButton, "Go back");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageEnglish, "English");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageChinese, "简体中文");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageGerman, "Deutsche");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageJapanese, "日本");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageKorean, "한국어");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageSpanish, "Español");
//        drawText.drawInCenterOfRect(canvas, textPaint, rect, settingsLanguageBackButton, "Go back");
    }

    //ALL MENUS ARE CLUMPED HERE
    public void drawMenus(Canvas canvas) {
        canvas.drawBitmap(shopMenu.getScaledBitmap(), null, shopMenu.getRect(), null);
        canvas.drawBitmap(shopSkinsSubMenu.getScaledBitmap(), null, shopSkinsSubMenu.getRect(), null);
        canvas.drawBitmap(myStuffMenu.getScaledBitmap(),  null, myStuffMenu.getRect(), null);

        drawOnLevelMenus(canvas);

        drawExitMenu(canvas);

        //shop menu buttons/submenus
        canvas.drawRect(shopBalloonsDisplay, menuButtonPaint);
        canvas.drawRect(shopBalloonsPriceTag, menuButtonPaint);
        canvas.drawRect(shopBalloonsBuy, menuButtonPaint);
        canvas.drawRect(shopRainDisplay, menuButtonPaint);
        canvas.drawRect(shopRainPriceTag, menuButtonPaint);
        canvas.drawRect(shopRainBuy, menuButtonPaint);
        canvas.drawRect(shopShellShockDisplay, menuButtonPaint);
        canvas.drawRect(shopShellShockPriceTag, menuButtonPaint);
        canvas.drawRect(shopShellShockBuy, menuButtonPaint);
        canvas.drawRect(shopSkins, menuButtonPaint);
        canvas.drawBitmap(shopSkinItem1Display.getScaledBitmap(), null, shopSkinItem1Display.getRect(), null);
        canvas.drawRect(shopSkinItem1PriceTag, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem1Buy, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem2Display, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem2PriceTag, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem2Buy, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem3Display, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem3PriceTag, subMenuButtonPaint);
        canvas.drawRect(shopSkinItem3Buy, subMenuButtonPaint);
        canvas.drawRect(shopSkinsBackButton, subMenuButtonPaint);
        canvas.drawRect(shopTitleCard, menuButtonPaint);
        canvas.drawRect(shopSkinsTitleCard, menuButtonPaint);
        canvas.drawRect(shopHud1, popUpPaint);
        canvas.drawRect(shopHud2, popUpPaint);
        canvas.drawRect(shopHud3, popUpPaint);

        //my stuff menu buttons
        canvas.drawRect(myStuffTitleCard, menuButtonPaint);
        canvas.drawRect(myBalloons, menuButtonPaint);
        canvas.drawRect(myRain, menuButtonPaint);
        canvas.drawRect(myShellShock, menuButtonPaint);
        canvas.drawBitmap(getMySkinCollection().get(0).get(4), null, selectedSkin, null);
        canvas.drawBitmap(getMySkinCollection().get(1).get(4), null, skinSlot1, null);
        canvas.drawRect(skinSlot2, menuButtonPaint);
        canvas.drawRect(skinSlot3, menuButtonPaint);
        canvas.drawRect(skinSlot4, menuButtonPaint);
        canvas.drawRect(skinSlot5, menuButtonPaint);
        canvas.drawRect(skinSlot6, menuButtonPaint);
        canvas.drawRect(skinSlot7, menuButtonPaint);
        canvas.drawRect(skinSlot8, menuButtonPaint);
        canvas.drawRect(skinSlot9, menuButtonPaint);
        canvas.drawRect(skinSlot10, menuButtonPaint);
        canvas.drawRect(skinSlot11, menuButtonPaint);
        canvas.drawRect(myHud1, popUpPaint);
        canvas.drawRect(myHud2, popUpPaint);
        canvas.drawRect(myHud3, popUpPaint);

        canvas.drawRect(shopConfirmMenu, mainMenuPaint);
        canvas.drawRect(shopConfirm, menuButtonPaint);
        canvas.drawRect(shopCancel, menuButtonPaint);
        canvas.drawRect(shopPricePopUp, popUpPaint);

        //throwaway portraits
        for (int i = 0; i < PortraitManager.getPortraits().size(); i++) {
            PortraitManager.getPortraits().get(i).draw(canvas);
        }

        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopPricePopUp, "-" + finalPrice);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopHud1, "+"+balloonInCart);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopHud2, "+"+rainPowersInCart);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopHud3, "+"+shellShocksInCart);

        drawText.drawInCenterOfRect(canvas, headerTextPaint, rect, myStuffTitleCard, "MY STUFF");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, myHud1, "" + getBalloons());
        drawText.drawInCenterOfRect(canvas, textPaint, rect, myHud2, "" + getRainDance());
        drawText.drawInCenterOfRect(canvas, textPaint, rect, myHud3, "" + getArms());

        //SHOP STUFF
        drawText.drawInCenterOfRect(canvas, headerTextPaint, rect, shopTitleCard, "SHOP");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinItem1Buy, "Buy");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinItem1PriceTag, "2,500");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinItem2Buy, "Buy");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinItem2PriceTag, "5,000");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinItem3Buy, "Buy");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinItem3PriceTag, "10,000");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopBalloonsBuy, "Buy");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopBalloonsPriceTag, "250");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopRainBuy, "Buy");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopRainPriceTag, "500");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopShellShockBuy, "Buy");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopShellShockPriceTag, "1,000");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkins, "Shop Skins");
        drawText.drawInCenterOfRect(canvas, subHeaderTextPaint, rect, shopSkinsTitleCard, "Skins");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopCancel, "Cancel");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopConfirm, "Confirm");
        drawText.drawInCenterOfRect(canvas, textPaint, rect, shopSkinsBackButton, "Go Back");

    }

    boolean counterLivesTimer = false;
    Paint headerFooterPaint = new Paint();
    Paint iconPaint = new Paint();
    Paint mailIconPaint = new Paint();

    @SuppressLint("ResourceAsColor")
    public void drawOnLevelHeader(Canvas canvas) {
        mailIconPaint.setColor(Color.LTGRAY);
        iconPaint.setColor(Color.YELLOW);
        headerFooterPaint.setColor(Color.GREEN);

        dynamicPaint.setColor(Color.CYAN);
        transparentPaint.setAntiAlias(true);
        transparentPaint.setColor(R.color.transparentColor);

        canvas.drawBitmap(mainHeader.getScaledBitmap(), null, mainHeader.getRect(), null); //top header
        canvas.drawRect(livesLeftTimer, iconPaint);
        canvas.drawBitmap(livesLeftIcon.getScaledBitmap(), null, livesLeftIcon.getRect(), null);
        canvas.drawBitmap(mailIcon.getScaledBitmap(), null, mailIcon.getRect(), null);
        canvas.drawBitmap(settingsIcon.getScaledBitmap(), null, settingsIcon.getRect(), null);
        canvas.drawRect(coinsIcon2, iconPaint);
        canvas.drawBitmap(coinsIcon1.getScaledBitmap(), null, coinsIcon1.getRect(), null);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(Constants.SCREEN_WIDTH/25f);
        textPaint.setTextAlign(Paint.Align.LEFT);

//        canvas.drawText(""+getLives(), Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/1.95f, (int) (Scale.UNIT*1.1), textPaint);
        drawText.drawInCenterOfRect(canvas, textPaint, rect, livesLeftIcon.getRect(), ""+getLives());
        if (getLives() < 5) {
            InternalClock.setLivesRunning(true);
            drawText.drawAlignLeftInRect(canvas, textPaint, rect, livesLeftTimer, InternalClock.getLivesTime(), (int) (Scale.UNIT*.8));
        }
        else {
            InternalClock.resetLivesTimer();
            drawText.drawAlignLeftInRect(canvas, textPaint, rect, livesLeftTimer, "Full", (int) (Scale.UNIT*.8));
        }



        textPaint.setTextAlign(Paint.Align.RIGHT);

//        canvas.drawText(""+getCoin(), (float) (Scale.UNIT*5.25), (int) (Scale.UNIT*1.1), textPaint);
        drawText.drawAlignRightInRect(canvas, textPaint, rect, coinsIcon2, ""+getCoin(), Scale.UNIT/3);

//        canvas.drawText("Settings", Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / 20f), (int) (Scale.UNIT*1.1), textPaint);
    }

    //CHECKOUT FUNCTION
//    private static boolean canAffordItem = false;
    private int itemsInCart = 0;
    private int balloonInCart = 0;
    private int rainPowersInCart = 0;
    private int shellShocksInCart = 0;
    private int finalPrice = 0;
    private int skin1InCart = 0;
    private static int checkoutState = 0;

//    public void setCanAffordItem(boolean canAffordItem) {
//        HomeInterface.canAffordItem = canAffordItem;
//    }
    public void setCheckoutState(int state) {
        HomeInterface.checkoutState = state;
    }
    public void travelToCartAnimation(int left, int top, int right, int bottom) {
        new PortraitManager(left, top, right, bottom, true);
        PortraitManager.getPortraits().get(PortraitManager.getPortraits().size()-1).getPhysics().bottom = bottom;
    }
    public void checkout(int state) {
        switch (state) {
            case 1:
                if (getCoin() - finalPrice >= 250) {
//                    canAffordItem = true;
                    balloonInCart++;
                    itemsInCart++;
                    finalPrice += 250;
                    //run animation method here.
                    Log.e(TAG, balloonInCart + " balloons in cart. final price = " + finalPrice);
                    travelToCartAnimation(Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), Scale.UNIT*4, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
                }
                checkoutState = 0;
                break;
            case 2:
                if (getCoin() -finalPrice >= 500){
                    rainPowersInCart++;
                    itemsInCart++;
                    finalPrice += 500;
                    //run animation here
                    travelToCartAnimation(Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (5*Scale.UNIT), Scale.UNIT*4, (Constants.SCREEN_HEIGHT/6) + (7*Scale.UNIT));

                }
                checkoutState = 0;
                break;
            case 3:
                if (getCoin() - finalPrice >= 1000) {
                    shellShocksInCart++;
                    itemsInCart++;
                    finalPrice += 1000;
                    travelToCartAnimation(Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (8*Scale.UNIT), Scale.UNIT*4, (Constants.SCREEN_HEIGHT/6) + (10*Scale.UNIT));
                }
                checkoutState = 0;
                break;
            case 4:
                //pull boolean check from skinBank to check if skin is already owned
                if (getCoin() - finalPrice >= 2500 && skin1InCart <=1 && !SkinBank.isSkinBOwned()) {
                    skin1InCart++;
                    itemsInCart++;
                    finalPrice += 2500;
                    travelToCartAnimation(Scale.UNIT*2, (Constants.SCREEN_HEIGHT/6) + (2*Scale.UNIT), Scale.UNIT*4, (Constants.SCREEN_HEIGHT/6) + (4*Scale.UNIT));
                }
                checkoutState = 0;
                break;
            case -1:
                confirmed = true;
                addToAssets();
                break;
            case -2:
                canceled = true;
                itemsInCart = 0;
                finalPrice = 0;
                balloonInCart = 0;
                rainPowersInCart = 0;
                shellShocksInCart = 0;
                checkoutState = 0;
                skin1InCart = 0;
                break;
        }
    }
    public void addToAssets() {
        addCoin(-finalPrice);
        addBalloon(balloonInCart);
        addRainPower(rainPowersInCart);
        addArms(shellShocksInCart);

        if (skin1InCart == 1) {
            getMySkinCollection().set(getSkinBankSize(), skinBank.getSkin_b());
//            skin1InCart = 2; //will be deleted after boolean check is pulled
            SkinBank.setSkinBOwned(true);
            incrementSkinBankSize();
            skin1InCart = 0;
        }

        finalPrice = 0;
        balloonInCart = 0;
        rainPowersInCart = 0;
        shellShocksInCart = 0;
        checkoutState = 0;
        itemsInCart = 0;
//        canAffordItem = false;
    }

    private boolean counterPlayButton = false;
    private void playMenuUp() {
        if (playState == 1) {
            Log.e(TAG, "playMenuUp: RUNNING");

            //it will never go back once play button is pressed
            if (!isInPlayState) {
                if (!counterPlayButton){
                    LevelInterface.levelPhysicsTimer.startLevelTimer1();
                    LevelInterface.levelPhysicsTimer.startTimer2();
                    homePhysicsTimer.startHomeTimer1();
                    homePhysicsTimer.startTimer2();
                    counterPlayButton = true;
                }
                playPhysics[0].swipeMiddleToLeft(playMenu, Constants.SCREEN_WIDTH);
                playPhysics[1].swipeMiddleToLeft(playButton, Scale.UNIT*4, Scale.UNIT/15);
                playPhysics[2].swipeMiddleToLeft(playHeader, Scale.UNIT*8, Scale.UNIT/15);
            }

            for (int i = 0; i < playPhysics.length; i++) {
                if (playPhysics[i].isMoving)
                    break;
                else if (!playPhysics[i].isMoving && i == playPhysics.length-1)
                    playState = 0;
            }
        }
    }

    //MENU FUNCTIONS
    private static int onHomeSavedState = 0;
    public void loadingMenuUp() {

        //LEVEL PRESSED
        if (loadingScreen1State == 1) {
            Log.e(TAG, "loadingMenuUp: RUNNING");
            onHomeSavedState = loadingScreen1State;
            if (isLoading1) {
                loading1Physics[0].swipeLeftToMiddle(loadingScreen,Constants.SCREEN_WIDTH + Scale.UNIT, Constants.SCREEN_WIDTH);
                loading1Physics[1].swipeLeftToMiddle(loadingScreenHint, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                loading1Physics[2].swipeLeftToMiddle(loadingScreenBar, Scale.UNIT*2, (int) (Scale.UNIT*3.5), Scale.UNIT/25f);
            } else {
                loading1Physics[0].swipeMiddleToLeft(loadingScreen, Constants.SCREEN_WIDTH + Scale.UNIT);
                loading1Physics[1].swipeMiddleToLeft(loadingScreenHint, Scale.UNIT*11);
                loading1Physics[2].swipeMiddleToLeft(loadingScreenBar, Scale.UNIT*11);
            }

            for (int i = 0; i < loading1Physics.length; i++) {
                if (loading1Physics[i].isMoving)
                    break;
                else if (isLoading1 && !loading1Physics[i].isMoving && i == loading1Physics.length-1) {
                    SceneManager.gameView = !SceneManager.gameView;
                    isLoading2 = true;
                    loadingScreen2State = 1;
                    loadingScreen1State = 0;
                } else if (!isLoading1 && !loading1Physics[i].isMoving && i == loading1Physics.length-1) {
                    loadingScreen1State = 0;
//                    loadingScreenBar.set(-Scale.UNIT*2, Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3, 0, (int) (Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3.25));
                }
            }

            //QUIT PRESSED
        } else if (loadingScreen1State == 2) {
            Log.e(TAG, "loadingMenuUp: RUNNING");
            onHomeSavedState = loadingScreen1State;
            if (isLoading1) {
                loading1Physics[0].swipeLeftToMiddle(loadingScreen,Constants.SCREEN_WIDTH + Scale.UNIT, Constants.SCREEN_WIDTH);
                loading1Physics[1].swipeLeftToMiddle(loadingScreenHint, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                loading1Physics[2].swipeLeftToMiddle(loadingScreenBar, Scale.UNIT*2, (int) (Scale.UNIT*3.5), Scale.UNIT/25f);
            } else {
                loading1Physics[0].swipeMiddleToLeft(loadingScreen, Constants.SCREEN_WIDTH + Scale.UNIT);
                loading1Physics[1].swipeMiddleToLeft(loadingScreenHint, Scale.UNIT*11);
                loading1Physics[2].swipeMiddleToLeft(loadingScreenBar, Scale.UNIT*11);
            }

            for (int i = 0; i < loading1Physics.length; i++) {
                if (loading1Physics[i].isMoving)
                    break;
                else if (isLoading1 && !loading1Physics[i].isMoving && i == loading1Physics.length-1) {
                    objectManager.quit();
                    isLoading2 = true;
                    loadingScreen2State = 1;
                    loadingScreen1State = 0;
                } else if (!isLoading1 && !loading1Physics[i].isMoving && i == loading1Physics.length-1) {
                    loadingScreen1State = 0;
//                    loadingScreenBar.set(-Scale.UNIT*2, Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3, 0, (int) (Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3.25));
                }
            }

            //RESTART PRESSED
        } else if (loadingScreen1State == 3) {
            Log.e(TAG, "loadingMenuUp: RUNNING");
            onHomeSavedState = loadingScreen1State;
            if (isLoading1) {
                loading1Physics[0].swipeLeftToMiddle(loadingScreen,Constants.SCREEN_WIDTH + Scale.UNIT, Constants.SCREEN_WIDTH);
                loading1Physics[1].swipeLeftToMiddle(loadingScreenHint, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                loading1Physics[2].swipeLeftToMiddle(loadingScreenBar, Scale.UNIT*2, (int) (Scale.UNIT*3.5), Scale.UNIT/25f);
            } else {
                loading1Physics[0].swipeMiddleToLeft(loadingScreen, Constants.SCREEN_WIDTH + Scale.UNIT);
                loading1Physics[1].swipeMiddleToLeft(loadingScreenHint, Scale.UNIT*11);
                loading1Physics[2].swipeMiddleToLeft(loadingScreenBar, Scale.UNIT*11);
            }

            for (int i = 0; i < loading1Physics.length; i++) {
                if (loading1Physics[i].isMoving)
                    break;
                else if (isLoading1 && !loading1Physics[i].isMoving && i == loading1Physics.length-1) {
                    objectManager.reset();
                    isLoading2 = true;
                    loadingScreen2State = 1;
                    loadingScreen1State = 0;
                    Log.e(TAG, "loadingMenuUp: is out of lives = " + isOutOfLives);
                } else if (!isLoading1 && !loading1Physics[i].isMoving && i == loading1Physics.length-1) {
                    loadingScreen1State = 0;
//                    loadingScreenBar.set(-Scale.UNIT*2, Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3, 0, (int) (Constants.SCREEN_HEIGHT/3 + Scale.UNIT*3.25));
                }
            }
        }

        if (loadingScreen2State == 1) {
            if (isLoading2) {
                loading2Physics[0].expandRect(loadingScreenBar, "right", (int) (Scale.UNIT*12.5), Scale.UNIT/18);
            }
            for (int i = 0; i < loading2Physics.length; i++) {
                if (loading2Physics[i].isMoving) {
                    break;
                } else if (!loading2Physics[i].isMoving && i == loading2Physics.length-1) {
                    loadingScreen2State = 0;
                    isLoading2 = false;
                    isLoading1 = false;
                    loadingScreen1State = onHomeSavedState;
                }
            }
        }
    }
    public void coinsMenuUp() {
        if (coinsMenuState == 1) {
            Log.e(TAG, "RUNNING");
            if (coinsSelected) {
                coinsPhysicsList[0].swipeLeftToMiddle(coinsDisplayHeader, (Scale.UNIT*10), (Scale.UNIT*12));
                coinsPhysicsList[1].swipeLeftToMiddle(coinsConfirmShowAd, (int) (Scale.UNIT*4.75),(int) (Scale.UNIT*12));
                coinsPhysicsList[2].swipeLeftToMiddle(coinsCancelShowAd,  (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*6.75));
                coinsPhysicsList[3].swipeLeftToMiddle(coinsMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
            } else {
                coinsPhysicsList[0].swipeMiddleToLeft(coinsDisplayHeader, (Scale.UNIT*10), Scale.UNIT/15);
                coinsPhysicsList[1].swipeMiddleToLeft(coinsConfirmShowAd, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                coinsPhysicsList[2].swipeMiddleToLeft(coinsCancelShowAd, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                coinsPhysicsList[3].swipeMiddleToLeft(coinsMenu, Scale.UNIT*11);
            }
            for (int i = 0; i < coinsPhysicsList.length; i++) {
                if (coinsPhysicsList[i].isMoving)
                    break;
                else if (!coinsPhysicsList[i].isMoving && i == coinsPhysicsList.length-1)
                    coinsMenuState = 0;
            }
        }
    }
    public void livesMenuUp() {
        if (livesMenuState == 1) {
            Log.e(TAG, "RUNNING");
            if (livesSelected) {
                livesPhysicsList[0].swipeLeftToMiddle(livesDisplayHeader, Scale.UNIT*10, Scale.UNIT*12);
                livesPhysicsList[1].swipeLeftToMiddle(livesConfirmShowAd, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*12));
                livesPhysicsList[2].swipeLeftToMiddle(livesCancelShowAd, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*6.75));
                livesPhysicsList[3].swipeLeftToMiddle(livesMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                if (isOutOfLives)
                    livesPhysicsList[4].swipeLeftToMiddle(livesOutOfLives, Scale.UNIT*5, (int) (Scale.UNIT*7.5), Scale.UNIT/30f);
            } else {
                livesPhysicsList[0].swipeMiddleToLeft(livesDisplayHeader, Scale.UNIT*10, Scale.UNIT/15);
                livesPhysicsList[1].swipeMiddleToLeft(livesConfirmShowAd, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                livesPhysicsList[2].swipeMiddleToLeft(livesCancelShowAd, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                livesPhysicsList[3].swipeMiddleToLeft(livesMenu, Scale.UNIT*11);
                livesPhysicsList[4].swipeMiddleToLeft(livesOutOfLives, Scale.UNIT*5, Scale.UNIT/30);
            }
            for (int i = 0; i < livesPhysicsList.length; i++) {
                if (livesPhysicsList[i].isMoving)
                    break;
                else if (!livesPhysicsList[i].isMoving && i == livesPhysicsList.length - 1)
                    livesMenuState = 0;
            }
        }
    }
    public void mailMenuUp() {
        if (mailMenuState == 1) {
            Log.e(TAG, "RUNNING");
            if (mailSelected && !mailSlot1Selected && !mailSlot2Selected && !mailSlot3Selected && !mailSlot4Selected) {
                mailPhysicsList[0].swipeLeftToMiddle(mailMenu, Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                mailPhysicsList[1].swipeLeftToMiddle(mailSlot1, Scale.UNIT*10, Scale.UNIT*12);
                mailPhysicsList[2].swipeLeftToMiddle(mailSlot2, Scale.UNIT*10, Scale.UNIT*12);
                mailPhysicsList[3].swipeLeftToMiddle(mailSlot3, Scale.UNIT*10, Scale.UNIT*12);
                mailPhysicsList[4].swipeLeftToMiddle(mailSlot4, Scale.UNIT*10, Scale.UNIT*12);
                mailPhysicsList[5].swipeLeftToMiddle(mailTitleCard, Scale.UNIT*6, Scale.UNIT*8, Scale.UNIT/30f);
            } else if (mailSelected) {
                mailPhysicsList[0].swipeMiddleToLeft(mailMenu, Scale.UNIT*11);
                mailPhysicsList[1].swipeMiddleToLeft(mailSlot1, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[2].swipeMiddleToLeft(mailSlot2, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[3].swipeMiddleToLeft(mailSlot3, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[4].swipeMiddleToLeft(mailSlot4, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[5].swipeLeftToMiddle(mailTitleCard, Scale.UNIT*6, Scale.UNIT*8, Scale.UNIT/30f);
            } else {
                mailPhysicsList[0].swipeMiddleToLeft(mailMenu, Scale.UNIT*11);
                mailPhysicsList[1].swipeMiddleToLeft(mailSlot1, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[2].swipeMiddleToLeft(mailSlot2, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[3].swipeMiddleToLeft(mailSlot3, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[4].swipeMiddleToLeft(mailSlot4, Scale.UNIT*10, Scale.UNIT/15);
                mailPhysicsList[5].swipeMiddleToLeft(mailTitleCard, Scale.UNIT*6, Scale.UNIT/30);
            }
            for (int i = 0; i < mailPhysicsList.length; i++) {
                if (mailPhysicsList[i].isMoving)
                    break;
                else if (!mailPhysicsList[i].isMoving && i == mailPhysicsList.length-1)
                    mailMenuState = 0;
            }
        }
    }
    public void mailSlot1Up() {
        if (mailSlot1State == 1) {
            Log.e(TAG, "mailSlot1Up: RUNNING");
            if (mailSlot1Selected) {
                mailSlot1Physics[0].swipeRightToMiddle(mailSlot1Menu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                mailSlot1Physics[1].swipeRightToMiddle(mailSlot1TextBox, Scale.UNIT*10, Scale.UNIT*2);
                mailSlot1Physics[2].swipeRightToMiddle(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT*2);
            } else {
                mailSlot1Physics[0].swipeMiddleToRight(mailSlot1Menu, Scale.UNIT*11);
                mailSlot1Physics[1].swipeMiddleToRight(mailSlot1TextBox, Scale.UNIT*10, Scale.UNIT/15);
                mailSlot1Physics[2].swipeMiddleToRight(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT/15);
            }
            for (int i = 0; i < mailSlot1Physics.length; i++) {
                if (mailSlot1Physics[i].isMoving)
                    break;
                else if (!mailSlot1Physics[i].isMoving && i == mailSlot1Physics.length-1)
                    mailSlot1State = 0;
            }
        }
    }
    public void mailSlot2Up() {
        if (mailSlot2State == 1) {
            Log.e(TAG, "mailSlot2Up: RUNNING");
            if (mailSlot2Selected) {
                mailSlot2Physics[0].swipeRightToMiddle(mailSlot2Menu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                mailSlot2Physics[1].swipeRightToMiddle(mailSlot2TextBox, Scale.UNIT*10, Scale.UNIT*2);
                mailSlot2Physics[2].swipeRightToMiddle(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT*2);
            } else {
                mailSlot2Physics[0].swipeMiddleToRight(mailSlot2Menu, Scale.UNIT*11);
                mailSlot2Physics[1].swipeMiddleToRight(mailSlot2TextBox, Scale.UNIT*10, Scale.UNIT/15);
//                mailSlot2Physics[2].swipeMiddleToRight(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT/15);
            }
            for (int i = 0; i < mailSlot2Physics.length; i++) {
                if (mailSlot2Physics[i].isMoving)
                    break;
                else if (!mailSlot2Physics[i].isMoving && i == mailSlot2Physics.length-1)
                    mailSlot2State = 0;
            }
        }
    }
    public void mailSlot3Up() {
        if (mailSlot3State == 1) {
            Log.e(TAG, "mailSlot3Up: RUNNING");
            if (mailSlot3Selected) {
                mailSlot3Physics[0].swipeRightToMiddle(mailSlot3Menu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                mailSlot3Physics[1].swipeRightToMiddle(mailSlot3TextBox, Scale.UNIT*10, Scale.UNIT*2);
                mailSlot3Physics[2].swipeRightToMiddle(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT*2);
            } else {
                mailSlot3Physics[0].swipeMiddleToRight(mailSlot3Menu, Scale.UNIT*11);
                mailSlot3Physics[1].swipeMiddleToRight(mailSlot3TextBox, Scale.UNIT*10, Scale.UNIT/15);
//                mailSlot3Physics[2].swipeMiddleToRight(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT/15);
            }
            for (int i = 0; i < mailSlot3Physics.length; i++) {
                if (mailSlot3Physics[i].isMoving)
                    break;
                else if (!mailSlot3Physics[i].isMoving && i == mailSlot3Physics.length-1)
                    mailSlot3State = 0;
            }
        }
    }
    public void mailSlot4Up() {
        if (mailSlot4State == 1) {
            Log.e(TAG, "mailSlot3Up: RUNNING");
            if (mailSlot4Selected) {
                mailSlot4Physics[0].swipeRightToMiddle(mailSlot4Menu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                mailSlot4Physics[1].swipeRightToMiddle(mailSlot4TextBox, Scale.UNIT*10, Scale.UNIT*2);
                mailSlot4Physics[2].swipeRightToMiddle(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT*2);
            } else {
                mailSlot4Physics[0].swipeMiddleToRight(mailSlot4Menu, Scale.UNIT*11);
                mailSlot4Physics[1].swipeMiddleToRight(mailSlot4TextBox, Scale.UNIT*10, Scale.UNIT/15);
//                mailSlot4Physics[2].swipeMiddleToRight(mailSlotBackButton, Scale.UNIT*10, Scale.UNIT/15);
            }
            for (int i = 0; i < mailSlot4Physics.length; i++) {
                if (mailSlot4Physics[i].isMoving)
                    break;
                else if (!mailSlot4Physics[i].isMoving && i == mailSlot4Physics.length-1)
                    mailSlot4State = 0;
            }
        }
    }
    public void settingsMenuUp() {
        if (settingsMenuState == 1) {
            Log.e(TAG, "RUNNING");
            if (settingsSelected && !settingsHowToPlaySelected && !settingsLanguageSelected) {
                settingsPhysicsList[0].swipeLeftToMiddle(settingsMenu, Scale.UNIT * 11, (int) (Scale.UNIT * 12.5));
                settingsPhysicsList[1].swipeLeftToMiddle(settingsGMV, Scale.UNIT * 4, Scale.UNIT * 6);
                settingsPhysicsList[2].swipeLeftToMiddle(settingsActualGMV, (int) (Scale.UNIT * 1.5), (Scale.UNIT * 8));
                settingsPhysicsList[3].swipeLeftToMiddle(settingsMinGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT * 10);
                settingsPhysicsList[4].swipeLeftToMiddle(settingsMaxGMV, (int) (Scale.UNIT * 1.5), (Scale.UNIT * 12));
                settingsPhysicsList[5].swipeLeftToMiddle(settingsSFX, Scale.UNIT * 4, Scale.UNIT * 6);
                settingsPhysicsList[6].swipeLeftToMiddle(settingsActualSFX, (int) (Scale.UNIT * 1.5), (Scale.UNIT * 8));
                settingsPhysicsList[7].swipeLeftToMiddle(settingsMinSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT * 10);
                settingsPhysicsList[8].swipeLeftToMiddle(settingsMaxSFX, (int) (Scale.UNIT * 1.5), (Scale.UNIT * 12));
                settingsPhysicsList[9].swipeLeftToMiddle(settingsHowToPlay, Scale.UNIT * 10, Scale.UNIT * 12);
                settingsPhysicsList[10].swipeLeftToMiddle(settingsLanguage, Scale.UNIT * 10, Scale.UNIT * 12);
                settingsPhysicsList[11].swipeLeftToMiddle(settingsTitleCard, Scale.UNIT * 6, Scale.UNIT * 8, Scale.UNIT / 30f);
            } else if (settingsSelected){
                settingsPhysicsList[0].swipeMiddleToLeft(settingsMenu, Scale.UNIT * 11);
                settingsPhysicsList[1].swipeMiddleToLeft(settingsGMV, Scale.UNIT * 4, Scale.UNIT / 15);
                settingsPhysicsList[2].swipeMiddleToLeft(settingsActualGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[3].swipeMiddleToLeft(settingsMinGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[4].swipeMiddleToLeft(settingsMaxGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[5].swipeMiddleToLeft(settingsSFX, Scale.UNIT * 4, Scale.UNIT / 15);
                settingsPhysicsList[6].swipeMiddleToLeft(settingsActualSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[7].swipeMiddleToLeft(settingsMinSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[8].swipeMiddleToLeft(settingsMaxSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[9].swipeMiddleToLeft(settingsHowToPlay, Scale.UNIT * 10, Scale.UNIT / 15);
                settingsPhysicsList[10].swipeMiddleToLeft(settingsLanguage, Scale.UNIT * 10, Scale.UNIT / 15);
                settingsPhysicsList[11].swipeLeftToMiddle(settingsTitleCard, Scale.UNIT * 6, Scale.UNIT * 8, Scale.UNIT / 30f);
            } else {
                settingsPhysicsList[0].swipeMiddleToLeft(settingsMenu, Scale.UNIT * 11);
                settingsPhysicsList[1].swipeMiddleToLeft(settingsGMV, Scale.UNIT * 4, Scale.UNIT / 15);
                settingsPhysicsList[2].swipeMiddleToLeft(settingsActualGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[3].swipeMiddleToLeft(settingsMinGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[4].swipeMiddleToLeft(settingsMaxGMV, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[5].swipeMiddleToLeft(settingsSFX, Scale.UNIT * 4, Scale.UNIT / 15);
                settingsPhysicsList[6].swipeMiddleToLeft(settingsActualSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[7].swipeMiddleToLeft(settingsMinSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[8].swipeMiddleToLeft(settingsMaxSFX, (int) (Scale.UNIT * 1.5), Scale.UNIT /15);
                settingsPhysicsList[9].swipeMiddleToLeft(settingsHowToPlay, Scale.UNIT * 10, Scale.UNIT / 15);
                settingsPhysicsList[10].swipeMiddleToLeft(settingsLanguage, Scale.UNIT * 10, Scale.UNIT / 15);
                settingsPhysicsList[11].swipeMiddleToLeft(settingsTitleCard, Scale.UNIT * 6, Scale.UNIT / 30);
            }
            for (int i = 0; i < settingsPhysicsList.length; i++) {
                if (settingsPhysicsList[i].isMoving)
                    break;
                else if (!settingsPhysicsList[i].isMoving && i == settingsPhysicsList.length - 1)
                    settingsMenuState = 0;
            }
        }
    }
    public void settingsHowToMenuUp() {
        if (settingsHowToPlayState == 1) {
            Log.e(TAG, "settingsHowToMenuUp: RUNNING");
            if (settingsHowToPlaySelected) {
                settingsHowToPhysics[0].swipeRightToMiddle(settingsHowToPlayMenu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                settingsHowToPhysics[1].swipeRightToMiddle(settingsHowToPlayDisplay, Scale.UNIT*10, Scale.UNIT*2);
                settingsHowToPhysics[2].swipeRightToMiddle(settingsBackButton, Scale.UNIT*10, Scale.UNIT*2);
            } else {
                settingsHowToPhysics[0].swipeMiddleToRight(settingsHowToPlayMenu, Scale.UNIT*11);
                settingsHowToPhysics[1].swipeMiddleToRight(settingsHowToPlayDisplay, Scale.UNIT*10, Scale.UNIT/15);
                settingsHowToPhysics[2].swipeMiddleToRight(settingsBackButton, Scale.UNIT*10, Scale.UNIT/15);
            }
            for (int i = 0; i < settingsHowToPhysics.length; i++) {
                if (settingsHowToPhysics[i].isMoving)
                    break;
                else if (!settingsHowToPhysics[i].isMoving && i == settingsHowToPhysics.length - 1)
                    settingsHowToPlayState = 0;
            }
        }
    }
    public void settingsLanguageMenuUp() {
        if (settingsLanguageState == 1) {
            Log.e(TAG, "settingsLanguageMenuIp: RUNNING");
            if (settingsLanguageSelected) {
                settingsLanguagePhysics[0].swipeRightToMiddle(settingsLanguageMenu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                settingsLanguagePhysics[1].swipeRightToMiddle(settingsLanguageEnglish, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*7.25));
                settingsLanguagePhysics[2].swipeRightToMiddle(settingsLanguageChinese, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*2));
                settingsLanguagePhysics[3].swipeRightToMiddle(settingsLanguageGerman, (int) (Scale.UNIT*4.75), Scale.UNIT*2);
                settingsLanguagePhysics[4].swipeRightToMiddle(settingsLanguageJapanese, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*7.25));
                settingsLanguagePhysics[5].swipeRightToMiddle(settingsLanguageKorean, (int) (Scale.UNIT*4.75), Scale.UNIT*2);
                settingsLanguagePhysics[6].swipeRightToMiddle(settingsLanguageSpanish, (int) (Scale.UNIT*4.75), (int) (Scale.UNIT*7.25));
                settingsLanguagePhysics[7].swipeRightToMiddle(settingsBackButton, Scale.UNIT*10, Scale.UNIT*2);
            } else {
                settingsLanguagePhysics[0].swipeMiddleToRight(settingsLanguageMenu, Scale.UNIT*11);
                settingsLanguagePhysics[1].swipeMiddleToRight(settingsLanguageEnglish, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                settingsLanguagePhysics[2].swipeMiddleToRight(settingsLanguageChinese, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                settingsLanguagePhysics[3].swipeMiddleToRight(settingsLanguageGerman, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                settingsLanguagePhysics[4].swipeMiddleToRight(settingsLanguageJapanese, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                settingsLanguagePhysics[5].swipeMiddleToRight(settingsLanguageKorean, (int) (Scale.UNIT*4.75), Scale.UNIT/15);
                settingsLanguagePhysics[6].swipeMiddleToRight(settingsLanguageSpanish, (int) (Scale.UNIT*4.75), Scale.UNIT/15);

                //this is to prevent double speed on back button rect
                if (settingsHowToPlayState == 0)
                    settingsLanguagePhysics[7].swipeMiddleToRight(settingsBackButton, Scale.UNIT*10, Scale.UNIT/15);
            }
            for (int i = 0; i < settingsLanguagePhysics.length; i++) {
                if (settingsLanguagePhysics[i].isMoving)
                    break;
                else if (!settingsLanguagePhysics[i].isMoving && i == settingsLanguagePhysics.length - 1)
                    settingsLanguageState = 0;
            }
        }
    }
    public void shopMenuUp() {
        if (shopMenuState ==1) {
            Log.e(TAG, "RUNNING");
            if (shopSelected && !shopSkinsSelected) {
                checkout(checkoutState);
                if (itemsInCart > 0){
                    //run confirm menu animation here
                    shopPhysicsList[0].swipeRightToMiddle(shopConfirmMenu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                    shopPhysicsList[1].swipeRightToMiddle(shopConfirm, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*8.25));
                    shopPhysicsList[2].swipeRightToMiddle(shopCancel, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*4.25));
                    shopPhysicsList[3].swipeLeftToMiddle(shopPricePopUp, Scale.UNIT*4, Scale.UNIT*4);
                } else {
                    shopPhysicsList[0].swipeMiddleToRight(shopConfirmMenu, Scale.UNIT*11);
                    shopPhysicsList[1].swipeMiddleToRight(shopConfirm, (int) (Scale.UNIT*3.75), Scale.UNIT/15);
                    shopPhysicsList[2].swipeMiddleToRight(shopCancel, (int) (Scale.UNIT*3.75), Scale.UNIT/15);
                    shopPhysicsList[3].swipeMiddleToLeft(shopPricePopUp, Scale.UNIT*4, Scale.UNIT/15);
                }
                shopPhysicsList[4].swipeLeftToMiddle(shopTitleCard, Scale.UNIT*6, Scale.UNIT*8,Scale.UNIT/30f);
                shopPhysicsList[5].swipeLeftToMiddle(shopHud1, Scale.UNIT, Scale.UNIT*4);
                shopPhysicsList[6].swipeLeftToMiddle(shopHud2, Scale.UNIT, Scale.UNIT*4);
                shopPhysicsList[7].swipeLeftToMiddle(shopHud3, Scale.UNIT, Scale.UNIT*4);
                shopPhysicsList[8].swipeLeftToMiddle(shopMenu.getRect(), Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                shopPhysicsList[9].swipeLeftToMiddle(shopBalloonsDisplay, Scale.UNIT*2, Scale.UNIT*4);
                shopPhysicsList[10].swipeLeftToMiddle(shopBalloonsPriceTag, (Scale.UNIT*5), (int) (Scale.UNIT*9.25));
                shopPhysicsList[11].swipeLeftToMiddle(shopBalloonsBuy, (int) (Scale.UNIT*2.5), Scale.UNIT*12);
                shopPhysicsList[12].swipeLeftToMiddle(shopRainDisplay, Scale.UNIT*2, Scale.UNIT*4);
                shopPhysicsList[13].swipeLeftToMiddle(shopRainPriceTag, Scale.UNIT*5, (int) (Scale.UNIT*9.25));
                shopPhysicsList[14].swipeLeftToMiddle(shopRainBuy, (int) (Scale.UNIT*2.5), Scale.UNIT*12);
                shopPhysicsList[15].swipeLeftToMiddle(shopShellShockDisplay, Scale.UNIT*2, Scale.UNIT*4);
                shopPhysicsList[16].swipeLeftToMiddle(shopShellShockPriceTag, Scale.UNIT*5, (int) (Scale.UNIT*9.25));
                shopPhysicsList[17].swipeLeftToMiddle(shopShellShockBuy, (int) (Scale.UNIT*2.5), Scale.UNIT*12);
                shopPhysicsList[18].swipeLeftToMiddle(shopSkins, Scale.UNIT*10, Scale.UNIT*12);
            }
            else if (shopSelected) {
                checkout(checkoutState);
                if (itemsInCart > 0){
                    //run confirm menu animation here
                    shopPhysicsList[0].swipeRightToMiddle(shopConfirmMenu, Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                    shopPhysicsList[1].swipeRightToMiddle(shopConfirm, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*8.25));
                    shopPhysicsList[2].swipeRightToMiddle(shopCancel, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*4.25));
                    shopPhysicsList[3].swipeLeftToMiddle(shopPricePopUp, Scale.UNIT*4, Scale.UNIT*4);
                } else {
                    shopPhysicsList[0].swipeMiddleToRight(shopConfirmMenu, Scale.UNIT*11);
                    shopPhysicsList[1].swipeMiddleToRight(shopConfirm, (int) (Scale.UNIT*3.75), Scale.UNIT/15);
                    shopPhysicsList[2].swipeMiddleToRight(shopCancel, (int) (Scale.UNIT*3.75), Scale.UNIT/15);
                    shopPhysicsList[3].swipeMiddleToLeft(shopPricePopUp, Scale.UNIT*4, Scale.UNIT/15);
                }
                shopPhysicsList[4].swipeLeftToMiddle(shopTitleCard, Scale.UNIT*6, Scale.UNIT*8,Scale.UNIT/30f);
                shopPhysicsList[5].swipeMiddleToLeft(shopHud1, Scale.UNIT, Scale.UNIT/15);
                shopPhysicsList[6].swipeMiddleToLeft(shopHud2, Scale.UNIT, Scale.UNIT/15);
                shopPhysicsList[7].swipeMiddleToLeft(shopHud3, Scale.UNIT, Scale.UNIT/15);
                shopPhysicsList[8].swipeMiddleToLeft(shopMenu.getRect(), Scale.UNIT*11);
                shopPhysicsList[9].swipeMiddleToLeft(shopBalloonsDisplay, Scale.UNIT*2, Scale.UNIT/15);
                shopPhysicsList[10].swipeMiddleToLeft(shopBalloonsPriceTag, (Scale.UNIT*5), Scale.UNIT/15);
                shopPhysicsList[11].swipeMiddleToLeft(shopBalloonsBuy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopPhysicsList[12].swipeMiddleToLeft(shopRainDisplay, Scale.UNIT*2, Scale.UNIT/15);
                shopPhysicsList[13].swipeMiddleToLeft(shopRainPriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopPhysicsList[14].swipeMiddleToLeft(shopRainBuy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopPhysicsList[15].swipeMiddleToLeft(shopShellShockDisplay, Scale.UNIT*2, Scale.UNIT/15);
                shopPhysicsList[16].swipeMiddleToLeft(shopShellShockPriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopPhysicsList[17].swipeMiddleToLeft(shopShellShockBuy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopPhysicsList[18].swipeMiddleToLeft(shopSkins, Scale.UNIT*10,  Scale.UNIT/15);
            }
            else {
                shopPhysicsList[0].swipeMiddleToRight(shopConfirmMenu, Scale.UNIT*11);
                shopPhysicsList[1].swipeMiddleToRight(shopConfirm, (int) (Scale.UNIT*3.75), Scale.UNIT/15);
                shopPhysicsList[2].swipeMiddleToRight(shopCancel, (int) (Scale.UNIT*3.75), Scale.UNIT/15);
                shopPhysicsList[3].swipeMiddleToLeft(shopPricePopUp, Scale.UNIT*4, Scale.UNIT/15);
                shopPhysicsList[4].swipeMiddleToLeft(shopTitleCard, Scale.UNIT*6,Scale.UNIT/30);
                shopPhysicsList[5].swipeMiddleToLeft(shopHud1, Scale.UNIT, Scale.UNIT/15);
                shopPhysicsList[6].swipeMiddleToLeft(shopHud2, Scale.UNIT, Scale.UNIT/15);
                shopPhysicsList[7].swipeMiddleToLeft(shopHud3, Scale.UNIT, Scale.UNIT/15);
                shopPhysicsList[8].swipeMiddleToLeft(shopMenu.getRect(), Scale.UNIT*11);
                shopPhysicsList[9].swipeMiddleToLeft(shopBalloonsDisplay, Scale.UNIT*2, Scale.UNIT/15);
                shopPhysicsList[10].swipeMiddleToLeft(shopBalloonsPriceTag, (Scale.UNIT*5), Scale.UNIT/15);
                shopPhysicsList[11].swipeMiddleToLeft(shopBalloonsBuy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopPhysicsList[12].swipeMiddleToLeft(shopRainDisplay, Scale.UNIT*2, Scale.UNIT/15);
                shopPhysicsList[13].swipeMiddleToLeft(shopRainPriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopPhysicsList[14].swipeMiddleToLeft(shopRainBuy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopPhysicsList[15].swipeMiddleToLeft(shopShellShockDisplay, Scale.UNIT*2, Scale.UNIT/15);
                shopPhysicsList[16].swipeMiddleToLeft(shopShellShockPriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopPhysicsList[17].swipeMiddleToLeft(shopShellShockBuy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopPhysicsList[18].swipeMiddleToLeft(shopSkins, Scale.UNIT*10,  Scale.UNIT/15);
            }
            for (int i = 0; i < shopPhysicsList.length; i++) {
                if (shopPhysicsList[i].isMoving)
                    break;
                else if (!shopPhysicsList[i].isMoving && i == shopPhysicsList.length - 1)
                    shopMenuState = 0;
            }
        }
    }
    public void shopSkinsSubMenuUp() {
        if (shopSkinsMenuState == 1) {
            Log.e(TAG, "RUNNING");
            if (shopSkinsSelected) {
                shopSkinsPhysicsList[0].swipeRightToMiddle(shopSkinsSubMenu.getRect(), Scale.UNIT*11, (int) (Scale.UNIT*1.5));
                shopSkinsPhysicsList[1].swipeRightToMiddle(shopSkinItem1Display.getRect(), Scale.UNIT*2, Scale.UNIT*2);
                shopSkinsPhysicsList[2].swipeRightToMiddle(shopSkinItem1PriceTag, Scale.UNIT*5, (int) (Scale.UNIT*4.25));
                shopSkinsPhysicsList[3].swipeRightToMiddle(shopSkinItem1Buy, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.5));
                shopSkinsPhysicsList[4].swipeRightToMiddle(shopSkinItem2Display, Scale.UNIT*2, Scale.UNIT*2);
                shopSkinsPhysicsList[5].swipeRightToMiddle(shopSkinItem2PriceTag, Scale.UNIT*5, (int) (Scale.UNIT*4.25));
                shopSkinsPhysicsList[6].swipeRightToMiddle(shopSkinItem2Buy, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.5));
                shopSkinsPhysicsList[7].swipeRightToMiddle(shopSkinItem3Display, Scale.UNIT*2, Scale.UNIT*2);
                shopSkinsPhysicsList[8].swipeRightToMiddle(shopSkinItem3PriceTag, Scale.UNIT*5, (int) (Scale.UNIT*4.25));
                shopSkinsPhysicsList[9].swipeRightToMiddle(shopSkinItem3Buy, (int) (Scale.UNIT*2.5), (int) (Scale.UNIT*9.5));
                shopSkinsPhysicsList[10].swipeRightToMiddle(shopSkinsBackButton, Scale.UNIT*10, Scale.UNIT*2);
                shopSkinsPhysicsList[11].swipeRightToMiddle(shopSkinsTitleCard, (int) (Scale.UNIT*3.75), (int) (Scale.UNIT*8.25), (Scale.UNIT/40f));
            } else {
                shopSkinsPhysicsList[0].swipeMiddleToRight(shopSkinsSubMenu.getRect(), Scale.UNIT*11);
                shopSkinsPhysicsList[1].swipeMiddleToRight(shopSkinItem1Display.getRect(), Scale.UNIT*2, Scale.UNIT/15);
                shopSkinsPhysicsList[2].swipeMiddleToRight(shopSkinItem1PriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopSkinsPhysicsList[3].swipeMiddleToRight(shopSkinItem1Buy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopSkinsPhysicsList[4].swipeMiddleToRight(shopSkinItem2Display, Scale.UNIT*2, Scale.UNIT/15);
                shopSkinsPhysicsList[5].swipeMiddleToRight(shopSkinItem2PriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopSkinsPhysicsList[6].swipeMiddleToRight(shopSkinItem2Buy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopSkinsPhysicsList[7].swipeMiddleToRight(shopSkinItem3Display, Scale.UNIT*2, Scale.UNIT/15);
                shopSkinsPhysicsList[8].swipeMiddleToRight(shopSkinItem3PriceTag, Scale.UNIT*5, Scale.UNIT/15);
                shopSkinsPhysicsList[9].swipeMiddleToRight(shopSkinItem3Buy, (int) (Scale.UNIT*2.5), Scale.UNIT/15);
                shopSkinsPhysicsList[10].swipeMiddleToRight(shopSkinsBackButton, Scale.UNIT*10, Scale.UNIT/15);
                shopSkinsPhysicsList[11].swipeMiddleToRight(shopSkinsTitleCard, (int) (Scale.UNIT*3.75), Scale.UNIT/30);
            }
            for (int i = 0; i < shopSkinsPhysicsList.length; i++) {
                if (shopSkinsPhysicsList[i].isMoving)
                    break;
                else if (!shopSkinsPhysicsList[i].isMoving && i == shopSkinsPhysicsList.length - 1)
                    shopSkinsMenuState = 0;
            }
        }
    }
    public void myStuffMenuUp() {
        if (myStuffMenuState == 1) {
            Log.e(TAG, "RUNNING");
            if (myStuffSelected) {
                myStuffPhysicsList[0].swipeLeftToMiddle(myStuffTitleCard, Scale.UNIT*6, Scale.UNIT*8,Scale.UNIT/30f);
                myStuffPhysicsList[1].swipeLeftToMiddle(myStuffMenu.getRect(), Scale.UNIT*11, (int) (Scale.UNIT*12.5));
                myStuffPhysicsList[2].swipeLeftToMiddle(myHud1, Scale.UNIT, Scale.UNIT*5);
                myStuffPhysicsList[3].swipeLeftToMiddle(myHud2, Scale.UNIT, (int) (Scale.UNIT*8.5));
                myStuffPhysicsList[4].swipeLeftToMiddle(myHud3, Scale.UNIT, Scale.UNIT*12);
                myStuffPhysicsList[5].swipeLeftToMiddle(myBalloons, Scale.UNIT*3,Scale.UNIT*5);
                myStuffPhysicsList[6].swipeLeftToMiddle(myRain, Scale.UNIT*3, (int) (Scale.UNIT*8.5));
                myStuffPhysicsList[7].swipeLeftToMiddle(myShellShock, Scale.UNIT*3, Scale.UNIT*12);
                myStuffPhysicsList[8].swipeLeftToMiddle(selectedSkin, Scale.UNIT*2, Scale.UNIT*4);
                myStuffPhysicsList[9].swipeLeftToMiddle(skinSlot1, Scale.UNIT*2, Scale.UNIT*7);
                myStuffPhysicsList[10].swipeLeftToMiddle(skinSlot2, Scale.UNIT*2, (int) (Scale.UNIT*9.5));
                myStuffPhysicsList[11].swipeLeftToMiddle(skinSlot3, Scale.UNIT*2, Scale.UNIT*12);
                myStuffPhysicsList[12].swipeLeftToMiddle(skinSlot4, Scale.UNIT*2, (int) (Scale.UNIT*4.5));
                myStuffPhysicsList[13].swipeLeftToMiddle(skinSlot5, Scale.UNIT*2, Scale.UNIT*7);
                myStuffPhysicsList[14].swipeLeftToMiddle(skinSlot6, Scale.UNIT*2, (int) (Scale.UNIT*9.5));
                myStuffPhysicsList[15].swipeLeftToMiddle(skinSlot7, Scale.UNIT*2, Scale.UNIT*12);
                myStuffPhysicsList[16].swipeLeftToMiddle(skinSlot8, Scale.UNIT*2, (int) (Scale.UNIT*4.5));
                myStuffPhysicsList[17].swipeLeftToMiddle(skinSlot9, Scale.UNIT*2, Scale.UNIT*7);
                myStuffPhysicsList[18].swipeLeftToMiddle(skinSlot10, Scale.UNIT*2, (int) (Scale.UNIT*9.5));
                myStuffPhysicsList[19].swipeLeftToMiddle(skinSlot11, Scale.UNIT*2, Scale.UNIT*12);
            } else {
                myStuffPhysicsList[0].swipeMiddleToLeft(myStuffTitleCard, Scale.UNIT*6, Scale.UNIT/30);
                myStuffPhysicsList[1].swipeMiddleToLeft(myStuffMenu.getRect(), Scale.UNIT*11);
                myStuffPhysicsList[2].swipeMiddleToLeft(myHud1, Scale.UNIT, Scale.UNIT/15);
                myStuffPhysicsList[3].swipeMiddleToLeft(myHud2, Scale.UNIT, Scale.UNIT/15);
                myStuffPhysicsList[4].swipeMiddleToLeft(myHud3, Scale.UNIT, Scale.UNIT/15);
                myStuffPhysicsList[5].swipeMiddleToLeft(myBalloons, Scale.UNIT*3, Scale.UNIT/15);
                myStuffPhysicsList[6].swipeMiddleToLeft(myRain, Scale.UNIT*3, Scale.UNIT/15);
                myStuffPhysicsList[7].swipeMiddleToLeft(myShellShock, Scale.UNIT*3, Scale.UNIT/15);
                myStuffPhysicsList[8].swipeMiddleToLeft(selectedSkin, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[9].swipeMiddleToLeft(skinSlot1, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[10].swipeMiddleToLeft(skinSlot2, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[11].swipeMiddleToLeft(skinSlot3, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[12].swipeMiddleToLeft(skinSlot4, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[13].swipeMiddleToLeft(skinSlot5, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[14].swipeMiddleToLeft(skinSlot6, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[15].swipeMiddleToLeft(skinSlot7, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[16].swipeMiddleToLeft(skinSlot8, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[17].swipeMiddleToLeft(skinSlot9, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[18].swipeMiddleToLeft(skinSlot10, Scale.UNIT*2, Scale.UNIT/15);
                myStuffPhysicsList[19].swipeMiddleToLeft(skinSlot11, Scale.UNIT*2, Scale.UNIT/15);
            }
            for (int i = 0; i < myStuffPhysicsList.length; i++) {
                if (myStuffPhysicsList[i].isMoving)
                    break;
                else if (!myStuffPhysicsList[i].isMoving && i == myStuffPhysicsList.length - 1)
                    myStuffMenuState = 0;
            }
        }
    }

    boolean confirmed = false;
    boolean canceled = false;

    //MENUS ALSO AVAILABLE ON GAME VIEW
    public void updateOnLevelMenus() {
        if (isAnyMenu()) {
            exitMenuPhysics.swipeLeftToMiddle(exitMenu.getRect(), Scale.UNIT, (Scale.UNIT*13));
            darkBackgroundPhysics.swipeLeftToMiddle(darkBackground, Scale.UNIT*15, Constants.SCREEN_WIDTH);
        } else if (LevelInterface.levelComplete || LevelInterface.gameOver1 || LevelInterface.gameOver2)
            darkBackgroundPhysics.swipeLeftToMiddle(darkBackground, Scale.UNIT*15, Constants.SCREEN_WIDTH);
        else {
            exitMenuPhysics.swipeMiddleToLeft(exitMenu.getRect(), Scale.UNIT, Scale.UNIT/15);
            darkBackgroundPhysics.swipeMiddleToLeft(darkBackground, Constants.SCREEN_WIDTH);
        }
        loadingMenuUp();
        coinsMenuUp();
        livesMenuUp();
        mailMenuUp();
        mailSlot1Up();
        mailSlot2Up();
        mailSlot3Up();
        mailSlot4Up();
        settingsMenuUp();
        settingsHowToMenuUp();
        settingsLanguageMenuUp();
    }

    @Override
    public void draw(Canvas canvas) {

        mailIconPaint.setColor(Color.LTGRAY);
        iconPaint.setColor(Color.YELLOW);
        headerFooterPaint.setColor(Color.GREEN);

        drawDarkBackground(canvas);
        drawOnLevelHeader(canvas);
        drawMenus(canvas);

        canvas.drawRect(shop, headerFooterPaint);
        canvas.drawRect(reCenter, headerFooterPaint);
        canvas.drawRect(myStuff, headerFooterPaint);

        drawText.drawCenterX(canvas, textPaint, "Shop", rect, Constants.SCREEN_HEIGHT - (Scale.UNIT/1.15f), 7.2f);
        drawText.drawCenterX(canvas, textPaint, "Re-Center", rect, Constants.SCREEN_HEIGHT - (Scale.UNIT/1.15f), 2f);
        drawText.drawCenterX(canvas, textPaint, "My Stuff", rect, Constants.SCREEN_HEIGHT - (Scale.UNIT/1.15f), 1f + 1/7.2f);

        drawLoadingScreen(canvas);
    }

    public boolean counterDecelerate = false;
    public int decelerationState = 0;
    public void setDecelerationDirection() {
        if (!counterDecelerate) {
            if (homePhysicsTimer.velocity > 0)
                decelerationState = 1;
            else
                decelerationState = -1;
        }
        counterDecelerate = true;
    }

    @Override
    public void update() {
        PlayerProgression.updateProgress();

        if (isReCentered && PlayerProgression.getPlayerProgressPosition().top < Constants.SCREEN_HEIGHT/3f){
            SCROLL = Constants.SCREEN_HEIGHT/3f - PlayerProgression.getPlayerProgressPosition().top;
        } else if (!ignoreAutoScroll){
            //this makes bound useless
            SCROLL += homePhysicsTimer.velocity;

            if (homePhysicsTimer.velocity != 0) {
                setDecelerationDirection();
                switch (decelerationState) {
                    case 1:
                        if (homePhysicsTimer.velocity > 0)
                            homePhysicsTimer.velocity -= (((float) Constants.SCREEN_WIDTH / 1000f));
                        else
                            homePhysicsTimer.velocity = 0;
                        break;
                    case -1:
                        if (homePhysicsTimer.velocity < 0)
                            homePhysicsTimer.velocity += (((float) Constants.SCREEN_WIDTH / 1000f));
                        else
                            homePhysicsTimer.velocity = 0;
                        break;
                }
            } else
                counterDecelerate = false;


            if (SCROLL <  0) {
                POSITION = 0;
                SCROLL = 0;
                ignoreAutoScroll = true;
            }

            if (SCROLL > Constants.SCREEN_HEIGHT/3f - getLevel10Button().top) {
                POSITION = -(Constants.SCREEN_HEIGHT/3f - getLevel10Button().top);
                SCROLL = Constants.SCREEN_HEIGHT/3f - getLevel10Button().top;
                ignoreAutoScroll = true;
            }
        } else
            ignoreAutoScroll = false;


        if (!PortraitManager.getPortraits().isEmpty()) {
            for (int i = 0; i < PortraitManager.getPortraits().size(); i++) {
                //THIS IS EXPENSIVE. FIX DURING OPTIMIZATION PHASE. LIMIT GETTERS to where I getPortraits only once.
                PortraitManager.getPortraits().get(i).getPhysics().travelToCartAnimation(PortraitManager.getPortraits().get(i).getRect());
                if (confirmed) {
                    PortraitManager.getPortraits().get(i).getPhysics().swipeRightReset(PortraitManager.getPortraits().get(i).getRect());
                } else if (canceled) {
                    PortraitManager.getPortraits().get(i).getPhysics().swipeLeftReset(PortraitManager.getPortraits().get(i).getRect());
                } else if (!shopSelected) {
                    PortraitManager.getPortraits().get(i).getPhysics().swipeMiddleToLeft(PortraitManager.getPortraits().get(i).getRect(), Scale.UNIT*2, Scale.UNIT/15);
                } else {
                    PortraitManager.getPortraits().get(i).getPhysics().swipeLeftToMiddle(PortraitManager.getPortraits().get(i).getRect(), Scale.UNIT*2, Scale.UNIT*4);
                }
            }
        } else {
            canceled = false;
            confirmed = false;
        }
        updateOnLevelMenus();
        shopMenuUp();
        shopSkinsSubMenuUp();
        myStuffMenuUp();
        playMenuUp();
    }
}