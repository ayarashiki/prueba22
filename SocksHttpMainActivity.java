package vps.build.vpn;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.Intent;
import java.security.MessageDigest;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.Vibrator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import io.michaelrocks.paranoid.Obfuscate;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import vps.build.tunnel.StatisticGraphData;
import vps.build.tunnel.StatisticGraphData.DataTransferStats;
import vps.build.tunnel.config.ConfigParser;
import vps.build.tunnel.config.ExceptionHandler;
import vps.build.tunnel.config.Settings;
import vps.build.tunnel.logger.ConnectionStatus;
import vps.build.tunnel.logger.SkStatus;
import vps.build.tunnel.tunnel.TunnelManagerHelper;
import vps.build.tunnel.tunnel.TunnelUtils;
import vps.build.vpn.activities.AboutActivity;
import vps.build.vpn.activities.BaseActivity;
import vps.build.vpn.activities.ConfigGeralActivity;
import vps.build.vpn.activities.SMSuPdater;
import vps.build.vpn.adapter.LogsAdapter;
import vps.build.vpn.adapter.SpinnerAdapter;
import vps.build.vpn.util.AESCrypt;
import vps.build.vpn.util.ConfigUpdate;
import vps.build.vpn.util.ConfigUtil;
import vps.build.vpn.util.Utils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import vps.build.tunnel.SocksHttpService;
import org.json.JSONException;
import org.json.JSONObject;
//import com.world.vps.R;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import android.app.NotificationManager;
import android.app.Notification;
import android.graphics.BitmapFactory;
import android.app.NotificationChannel;
import android.widget.AdapterView;
//import com.google.android.gms.ads.InterstitialAd;
import vps.build.vpn.util.ToastUtil;

//import androidx.appcompat.app.NotificationCompat;

import android.view.LayoutInflater;
import android.util.*;


import android.content.*;
import android.widget.*;
import android.view.*;

import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;

import vps.build.tunnel.util.sanaoll;
import vps.build.vpn.activities.MyIP_Address;

import com.google.firebase.analytics.FirebaseAnalytics;


@Obfuscate
public class SocksHttpMainActivity extends BaseActivity
implements View.OnClickListener,  SkStatus.StateListener
{

	public static final String app_name = "HTTP ZERO VPN";
	public static final String pckg = "com.sshprominex.vps";


	private ToastUtil toastutil;
	private boolean isConnected  = true;
    private CoordinatorLayout coordinatorLayout;
	public static String your_link = new String(new byte[]{104,116,116,112,115,58,47,47,98,105,116,98,105,110,46,105,116,47,54,105,55,56,48,89,72,97,47,114,97,119,47});

	private FirebaseAnalytics mFirebaseAnalytics;
	private ImageView ja123;
	
	
	private boolean mShown, mShown2;
	public String versionName;

	private View view6;
	private View view5;
    private View view4;
	private View view3;
    private View view2;
    private View view1;
//    private InterstitialAd mInterstitialAd;
	private GuideView mGuideView;
    public static int PICK_FILE = 1;
	private static final String DNS_BIN = "libdns";
	private Process dnsProcess;
	private File filedns;
	//Torrent
	
	private String[] torrentList = new String[] {"com.xunlei.downloadprovider",
		"torrentvillalite.romreviewer.com",
		/*"torrentvilla.romreviwer.com",
		"com.epic.app.iTorrent",
		"com.delphicoder.flud",
		"hu.bute.daai.amorg.drtorrent",
		"com.mobilityflow.torrent.prof",
		"com.microsoft.azure",
		"com.brute.torrentolite",
		"com.nebula.swift",
		"tv.bitx.media",
		"com.DroiDownloader",
		"bitking.torrent.downloader",
		"org.transdroid.lite",
		"com.mobilityflow.tvp",
		"com.gabordemko.torrnado",
		"com.frostwire.android",
		"com.vuze.android.remote",
		"com.akingi.torrent",
		"com.utorrent.web",
		"com.paolod.torrentsearch2",
		"com.delphicoder.flud.paid",
		"com.teeonsoft.ztorrent",
		"megabyte.tdm",
		"com.bittorrent.client.pro",
		"com.mobilityflow.torrent",
		"com.utorrent.client",
		"com.utorrent.client.pro",
		"com.bittorrent.client",
		"torrent",
		"com.AndroidA.DroiDownloader",
		"com.indris.yifytorrents",
		"com.delphicoder.flud",
		"com.oidapps.bittorrent",
		"dwleee.torrentsearch",
		"com.vuze.torrent.downloader",
		"megabyte.dm",
		"com.marutigroup.downtor",
		"com.guoshi.httpcanary",
		"com.fgrouptech.kickasstorrents",
		"in.gopalakrishnareddy.torrent",
		"com.jrummyapps.rootbrowser.classic",
		"com.bittorrent.client",
		"hu.tagsoft.ttorrent.lite",
		"co.we.torrent",
		"player.normal.np",
		"bin.mt.plus",
		"com.gmail.heagoo.apkeditor.pro",
		"com.minhui.networkcapture",
		"app.greyshirts.sslcapture",
		"com.packagesniffer.frtparlak",
		"jp.co.taosoftware.android.packetcapture",
		"com.emanuelef.remote_capture",
		"com.googlecode.networklog",
		"com.marutigroup.downtor",*/
		"com.minhui.wifianalyzer"};
		
		//End Torrent
	
	private static final String UPDATE_VIEWS = "MainUpdate";
	private String unityGameID = "GAME ID";
	private Boolean testMode = true;
	private String bottomAdUnitId = "Banner_Android";
	private String RewardadUnitId = "Rewarded_Android";
	public static LogsAdapter mAdapter;
	private Settings mConfig;
	private Toolbar toolbar_main;
	private Handler mHandler;
	private String token;
	private SweetAlertDialog mDialog;
	private CuboidButton starterButton;
	private ImageButton btnMenu;
    private DrawerLayout drawerLayout;
    private RippleBackground rippleBackground;
	private AdView adsBannerView;
	private ConfigUtil config;
	private TextView status;
	private Spinner serverSpinner;
	private SpinnerAdapter serverAdapter;
	private ArrayList<JSONObject> serverList;
	private SweetAlertDialog nops;
	private SweetAlertDialog progressDialog;
	private RecyclerView logList;
	private BottomSheetBehavior<View> bottomSheetBehavior;
	private View bshl;
	private SharedPreferences sp;
	private static final int START_VPN_PROFILE = 2002;
	private TextView bytesIn;
	private TextView bytesOut;


	  private void openlogs(){

		if (!(((String) getPackageManager().getApplicationLabel(getApplicationInfo())).equals(SocksHttpMainActivity.app_name) && getPackageName().equals(SocksHttpMainActivity.pckg))) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.HiroDialog);
			builder.setView(getLayoutInflater().inflate(R.layout.layout_spinner_row,null));
			builder.setCancelable(false);
			builder.setPositiveButton("Exit", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						// TODO: Implement this method
						if (android.os.Build.VERSION.SDK_INT >= 21) {
							finishAndRemoveTask();
						} else {
							android.os.Process.killProcess(android.os.Process.myPid());
						}
						System.exit(0);

					}
				});
			builder.show();

		}
	}


	@Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
		openlogs();
		toastutil = new ToastUtil(this);
		new SMSuPdater(this);
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		new TorrentDetection(this, torrentList).init();
		mHandler = new Handler();
		mConfig = new Settings(this);
		SharedPreferences prefs = getSharedPreferences(SocksHttpApp.PREFS_GERAL, Context.MODE_PRIVATE);
		boolean showFirstTime = prefs.getBoolean("connect_first_time", true);
		if (showFirstTime)
        {
            SharedPreferences.Editor pEdit = prefs.edit();
            pEdit.putBoolean("connect_first_time", false);
            pEdit.apply();
			Settings.setDefaultConfig(this);
			showBoasVindas();
        }
		doLayout();
		initBytesInAndOut();
		doUpdateLayout();
		sanaoll.CharlieProtect();

	}
	
	//Detectar torrent

	public class TorrentDetection 
	{

		int UNINSTALL_REQUEST_CODE = 1;

		private Context context;

		private String[] items;

		public TorrentDetection(Context c, String[] i) {
			context = c;
			items = i;
		}

		private boolean check(String uri)
		{
			PackageManager pm = context.getPackageManager();
			boolean app_installed = false;
			try
			{
				pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
				app_installed = true;
			}
			catch (PackageManager.NameNotFoundException e)
			{
				app_installed = false;
			}
			return app_installed;
		}

		void check() {
			for (int i=0;i < items.length ;i++)
			{
				if(check(items[i])){
					alert(items[i]);
					break;
				}
			}
		}

		public void init() {
			final Handler handler = new Handler();
			Timer timer = new Timer();
			TimerTask doAsynchronousTask = new TimerTask() {
				@Override
				public void run()
				{
					handler.post(new Runnable() {
							public void run()
							{
								check();
							}
						});
				}
			};
			timer.schedule(doAsynchronousTask, 0, 3000);
		}

		void alert(String app) {
			if (SocksHttpService.isRunning)
			{
				context.stopService(new Intent(context, SocksHttpService.class));
			}

			SweetAlertDialog mDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
			mDialog.setTitleText("App Prohibida Detectada!");
			mDialog.setContentText("Aplicacion Prohibida Instalada\nDesinstalala para usar la App!\nAplicacion: "+app);
			mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener(){
					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog)
					{
						System.exit(0);

					}});

			mDialog.setCancelable(false);
			mDialog.show();

		}

	}
	//Detectar torrent

	void initBytesInAndOut() {
        bytesIn = (TextView) findViewById(R.id.bytes_in);
        bytesOut = (TextView) findViewById(R.id.bytes_out);
		StatisticGraphData.getStatisticData().setDisplayDataTransferStats(true);
    }

	private void updateHeaderCallback() {
		DataTransferStats dataTransferStats = StatisticGraphData.getStatisticData().getDataTransferStats();
		bytesIn.setText(Utils.byteCountToDisplaySize(dataTransferStats.getTotalBytesReceived(), false));
		bytesOut.setText(Utils.byteCountToDisplaySize(dataTransferStats.getTotalBytesSent(), false));
	}

    private void doLayout() {
        setContentView(R.layout.activity_main_drawer);
		
		//UnityAds
		

		
		//End UnityAds

        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar_main);
		
		//Torrent
		
		new TorrentDetection(this, torrentList).init();

		// set ADS
        adsBannerView = (AdView) findViewById(R.id.adBannerMainView);

        if (!BuildConfig.DEBUG) {
            //adsBannerView.setAdUnitId(SocksHttpApp.ADS_UNITID_BANNER_MAIN);
        }

        if (TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
            adsBannerView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        if (adsBannerView != null) {
                            adsBannerView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            adsBannerView.loadAd(new AdRequest.Builder()
                                 .build());
		}

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView NavigationView = (NavigationView) findViewById(R.id.shitstuff);
		NavigationView.setItemIconTintList(null);


        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem)
                {

                    switch (menuItem.getItemId())
                    {
						case R.id.notif:
							drawerLayout.closeDrawers();
                            Changelogs();
                            return true;
                        case R.id.item1:
							drawerLayout.closeDrawers();
                            updateConfig(false);
                            return true;
                        case R.id.item2:
							drawerLayout.closeDrawers();
                            new UpdateCore(SocksHttpMainActivity.this, "http://ip-api.com/json", new UpdateCore.Listener() {
                                    @Override
                                    public void onLoading()
                                    {
                                        showSnackBar("Searching...");
                                    }

                                    @Override
                                    public void onCompleted(String config) throws Exception
                                    {
                                        JSONObject geo = new JSONObject(config);
                                        StringBuffer sb = new StringBuffer();
										sb.append("\n").append("ISP: ").append(geo.getString("isp"));
										sb.append("\n").append("Time Zone: ").append(geo.getString("timezone"));
										sb.append("\n").append("Country Code: ").append(geo.getString("countryCode"));
										sb.append("\n").append("Country: ").append(geo.getString("country"));
										sb.append("\n").append("Region: ").append(geo.getString("regionName"));
										sb.append("\n").append("City: ").append(geo.getString("city"));


										LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
										View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
										AlertDialog.Builder builer = new AlertDialog.Builder(SocksHttpMainActivity.this);
										builer.setView(inflate);
										ImageView iv = inflate.findViewById(R.id.icon);
										TextView title = inflate.findViewById(R.id.title);
										TextView ms = inflate.findViewById(R.id.message);
										TextView bubu = inflate.findViewById(R.id.positiveTxt);
										iv.setImageResource(R.drawable.ic_map_marker_radius);
										title.setText("Geo Location");
										ms.setText(sb.toString());
										bubu.setText("Ok,Close");
										final AlertDialog alert = builer.create();
										alert.setCancelable(false);
										alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
										//alert.getWindow().setGravity(Gravity.CENTER);
										alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
										alert.show();
										bubu.setOnClickListener(new View.OnClickListener() {
												@Override
												public void onClick(View v) {
													try
													{
														alert.dismiss();

													}
													catch (Exception e)
													{
														e.printStackTrace();
													}

												}});

										alert.show();
									}


                                    @Override
                                    public void onCancelled()
                                    {

                                    }

                                    @Override
                                    public void onException(String ex)
                                    {
										Toast.makeText(SocksHttpMainActivity.this, ex, Toast.LENGTH_SHORT).show();
                                    }
                                }).execute();
							return true;
                        /*case R.id.item3:
                            Intent intentSettings = new Intent(SocksHttpMainActivity.this, ConfigGeralActivity.class);
							//intentSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intentSettings);
							overridePendingTransition(R.anim.up_enter,R.anim.up_exit);
                            return true;*/
						
						/*case R.id.HardwareID:
							drawerLayout.closeDrawers();
							String HardwareID = android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
							ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
							ClipData clip = ClipData.newPlainText("text",  HardwareID);
							clipboard.setPrimaryClip(clip);
							LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
							AlertDialog.Builder builer = new AlertDialog.Builder(SocksHttpMainActivity.this); 
							builer.setView(inflate); 
							ImageView iv = inflate.findViewById(R.id.icon);
							TextView title = inflate.findViewById(R.id.title);
							TextView ms = inflate.findViewById(R.id.message);
							TextView bubu = inflate.findViewById(R.id.positiveTxt);
							iv.setImageResource(R.drawable.ic_dev);
							title.setText("Identificador Copiado");
							ms.setText(HardwareID);
							bubu.setText("Aceptar");
							final AlertDialog alert = builer.create(); 
							alert.setCancelable(false);
							alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
							//alert.getWindow().setGravity(Gravity.CENTER);
							alert.getWindow().getAttributes().windowAnimations = R.style.dialog;        
							alert.show();        
							bubu.setOnClickListener(new View.OnClickListener() { 
									@Override 
									public void onClick(View v) { 
										try
										{
											alert.dismiss();

										}
										catch (Exception e)
										{
											e.printStackTrace();
										}

									}});

							break;*/

                         //case R.id.miSendFeedback:
                          //  String url1 = "https://wa.me/59169008438";
                      //      Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
                            // intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //    startActivity(Intent.createChooser(intent1, getText(R.string.open_with)));
                       //     break;

						case R.id.hostshare:
							Intent hostshare2 = new Intent(SocksHttpMainActivity.this, clay.wifi.ui.ProxySettings.class);
							startActivity(hostshare2);
							break;

							case R.id.telegramchannel:
                            String url2 = "https://t.me/LuisAngel34";
                            Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                            // intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent.createChooser(intent2, getText(R.string.open_with)));
                            break;

                        case R.id.whatsapp:
                            String url3 = "https://wa.me/59163157134";
                            Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(url3));
                            // intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent.createChooser(intent3, getText(R.string.open_with)));
                            break;

						//case R.id.whatsapp_group:
                          //  String url5 = "https://t.me/prominex";
                        //    Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse(url5));
                            // intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         //   startActivity(Intent.createChooser(intent5, getText(R.string.open_with)));
                         //   break;

                        case R.id.donate:
                            String url4 = "http://www.paypal.me/Nemesisvpn";
                            Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(url4));
                            // intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent.createChooser(intent4, getText(R.string.open_with)));
                            break;

                        case R.id.speedtest:
                            Intent speedtestintent = new Intent(SocksHttpMainActivity.this, pktspeed.class);
                            startActivity(speedtestintent);
                            break;

						case R.id.checku:
							drawerLayout.closeDrawers();
							checkUser();
							break;

                        case R.id.miAbout:
                            Intent miAboutintent = new Intent(SocksHttpMainActivity.this, AboutActivity.class);
                            startActivity(miAboutintent);
                            break;

                        case R.id.ip:
                            Intent MyIpAddressintent = new Intent(SocksHttpMainActivity.this, MyIP_Address.class);
                            startActivity(MyIpAddressintent);
                            break;

						case R.id.Restore:
							drawerLayout.closeDrawers();
                            clearz();
                            return true;

                        default:
                        //    snack("Coming Soon!");
                            return true;

                    }
                    return false;
                }
            });

		view1 = findViewById(R.id.serverSpinner);
        view2 = findViewById(R.id.monsour_stats);
        view3 = findViewById(R.id.bottom_sheet);
        view4 = findViewById(R.id.activity_starterButtonMain);
        view5 = findViewById(R.id.bytes_in);
        view6 = findViewById(R.id.bytes_out);
        
		
		
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
		btnMenu = (ImageButton) findViewById(R.id.btnMenu);
		btnMenu.setOnClickListener(this);
        rippleBackground = (RippleBackground) findViewById(R.id.content);
		starterButton = (CuboidButton) findViewById(R.id.activity_starterButtonMain);
		starterButton.setOnClickListener(this);
		status = (TextView) findViewById(R.id.monsour_stats);
		config = new ConfigUtil(this);
		serverSpinner = (Spinner) findViewById(R.id.serverSpinner);
		serverList = new ArrayList<>();
		serverAdapter = new SpinnerAdapter(this, R.id.serverSpinner, serverList);
		serverSpinner.setAdapter(serverAdapter);
		loadServer();
		updateConfig(true);
		SharedPreferences sPrefs = mConfig.getPrefsPrivate();
		sPrefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
		sPrefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
		TextView configVer = (TextView) findViewById(R.id.config_v);           //esto es la vercion en la pantalla neko
		configVer.setText(config.getVersion());
		View bottomSheet = findViewById(R.id.bottom_sheet); 
		this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
		this.bshl = findViewById(R.id.bshl);
		serverSpinner.setSelection(sPrefs.getInt("LastSelectedServer", 0));
        serverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                    SharedPreferences sPrefs = mConfig.getPrefsPrivate();
                    SharedPreferences.Editor edit = sPrefs.edit();
                    edit.putInt("LastSelectedServer", p3).apply();
                }

                @Override
                public void onNothingSelected(AdapterView<?> p1) {
                }
            });
		
        View persistentbottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(persistentbottomSheet);
		behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { 
				@Override 
				public void onStateChanged(@NonNull View view, int i) { 
					switch (i){ 
						case BottomSheetBehavior.STATE_HIDDEN:
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED: 
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            behavior.setHideable(false);
                            break;
					} 
				} 
				@Override 
				public void onSlide(@NonNull View view, float slideOffset) { 

				} 
			});
			
		bshl.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED)
                    {
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        //btnBottomSheet.setText(R.string.close);
                    }
                    else
                    {
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        //btnBottomSheet.setText(R.string.expand);
                    }
                }
            });
			

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		mAdapter = new LogsAdapter(layoutManager, this);
		logList = (RecyclerView) findViewById(R.id.recyclerLog);
		logList.setAdapter(mAdapter);
		logList.setLayoutManager(layoutManager);
		mAdapter.scrollToLastPosition();
		boolean isRunning = SkStatus.isTunnelActive();
        if (isRunning) {
            serverSpinner.setEnabled(false);
        } else {
            serverSpinner.setEnabled(true);
        }
	}


	private void showUserInfo(final String result) {
		//MUESTRA FECHA Y COMPROBACIONES
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date hoje = new Date();
		final String CurrentDate = dateFormat.format(hoje);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				//FORMATA DATA
				String data = formatDate(result);

				//COMPRUEBE LOS DÍAS RESTANTES
				try {
					Date firstDate = sdf.parse(CurrentDate);
					Date secondDate = sdf.parse(data);

					long diff = secondDate.getTime() - firstDate.getTime();
					TimeUnit time = TimeUnit.DAYS;
					long dias_diferenca = time.convert(diff, TimeUnit.MILLISECONDS);
					int dias_diferenca_int = (int) dias_diferenca;
					String id_token = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

					mDialog = new SweetAlertDialog(SocksHttpMainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
					//mDialog.getProgressHelper().setBarColor(Color.parseColor("#206ba3"));
					mDialog.setTitleText("Tiempo Restante !!");
					mDialog.setContentText(Html.fromHtml("<b><font color='#FF0000'>ID Token: </color></b>") + id_token + "\n\n" + Html.fromHtml("<b><font color='#FF0000'>Tiempo Restante </color></b>") + dias_diferenca_int + " Dias" + "\n\n" + Html.fromHtml("<b><font color='#FF0000'>Fecha de Expiracion</color></b>") + "\n" + data);
					mDialog.setCancelable(true);
					mDialog.show();

					if (dias_diferenca_int <= 3){
						//   avisos.setText("Atenção!\nRestam "+ dias_diferenca_int + " dias para o vencimento do seu login!\n Por favor,contate o suporte");
					}else{
						//   avisos.setText("");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}


	public static String formatDate(String data){
		String dateStr = data;
		String returnString = "";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(dateStr);
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateStr = sdf.format(date);
			returnString = dateStr;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}




	private void checkUser() {
		int pos1 = serverSpinner.getSelectedItemPosition();
		try {
			final String URLcake = config.getServersArray().getJSONObject(pos1).getString("ServerUser");
			String current_user2 = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

			if(TextUtils.isEmpty(current_user2)){

			}else {
				final String current_user = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

				Log.i("cake2", URLcake);
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							URL url = new URL(URLcake);
							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
							conn.setRequestMethod("POST");
							conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
							conn.setRequestProperty("Accept", "application/json");
							conn.setDoOutput(true);
							conn.setDoInput(true);

							JSONObject jsonParam = new JSONObject();
							jsonParam.put("user", current_user);

							Log.i("JSON", jsonParam.toString());

							DataOutputStream os = new DataOutputStream(conn.getOutputStream());
							os.writeBytes(jsonParam.toString());
							os.flush();
							os.close();

							if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
								try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
									String result;

									while ((result = bufferedReader.readLine()) != null) {
										if (result.equals("not exist")) {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {

													new SweetAlertDialog(SocksHttpMainActivity.this, SweetAlertDialog.ERROR_TYPE)
															.setTitleText("Error !!")
															.setContentText("Usuario Expirado o no Registrado")
															.show();
												}
											});

										} else {
											showUserInfo(result);
										}
										Log.d("JSON", result);

									}
								}
							} else if (conn.getResponseCode() == 401) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {

										new SweetAlertDialog(SocksHttpMainActivity.this, SweetAlertDialog.ERROR_TYPE)
												.setTitleText("Error !!")
												.setContentText("Ocurrio un error al obtener el Usuario")
												.show();
									}
								});
								//VERIFICANDO ERROR
							} else {
								new SweetAlertDialog(SocksHttpMainActivity.this, SweetAlertDialog.ERROR_TYPE)
										.setTitleText("Error !!")
										.setContentText("Ocurrio un error al obtener el Usuario")
										.show();
							}

							conn.disconnect();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				thread.start();
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}
		//final String URLcake = "http://45.32.165.176:6888/checkUser/";
	}



	public void copiartoken(View view) {
		String idHd = android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
		String hadweridr = (idHd);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View inflate = inflater.inflate(R.layout.alerta_token, (ViewGroup) null);
		AlertDialog.Builder builer = new AlertDialog.Builder(this);
		builer.setView(inflate);
		TextView ms = inflate.findViewById(R.id.hadsTextView2);
		TextView ok = inflate.findViewById(R.id.hadsButton);
		ImageView no = inflate.findViewById(R.id.hadsButton2);
		ms.setText("Su token es: " + hadweridr + "\n\nDale copiar token y enviaselo a tu distribuidor");
		ok.setText("Copiar Token");
		final AlertDialog alert = builer.create();
		alert.setCanceledOnTouchOutside(false);
		alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		alert.getWindow().setGravity(android.view.Gravity.CENTER);
		alert.show();
		no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alert.dismiss();
			}
		});
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				{

					ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					ClipData clip = ClipData.newPlainText("text",  hadweridr);
					clipboard.setPrimaryClip(clip);
					Toast.makeText(getApplicationContext(),"Token Copiado con Exito!",Toast.LENGTH_SHORT).show();
					alert.dismiss();
				}

			}});

		alert.show();
	}

	public void timeleftt(View view) {
		checkUser();
	}

	private void doUpdateLayout() {
		SharedPreferences prefs = mConfig.getPrefsPrivate();
		//AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
		boolean isRunning = SkStatus.isTunnelActive();
		int tunnelType = prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);

		setStarterButton(starterButton, this);

		switch (tunnelType) {
			case Settings.bTUNNEL_TYPE_SSH_DIRECT:
				((AppCompatRadioButton) findViewById(R.id.activity_mainSSHDirectRadioButton))
					.setChecked(true);
				break;

			case Settings.bTUNNEL_TYPE_SSH_PROXY:
				((AppCompatRadioButton) findViewById(R.id.activity_mainSSHProxyRadioButton))
					.setChecked(true);
				break;
		}

        if (isRunning) {
            serverSpinner.setEnabled(false);
        } else {
            serverSpinner.setEnabled(true);
        }
	}



	private void setStarterButton(CuboidButton starterButton, SocksHttpMainActivity p1) {
	}

	private synchronized void doSaveData() {
		try {
			SharedPreferences prefs = mConfig.getPrefsPrivate();
			SharedPreferences.Editor edit = prefs.edit();
			
			edit.apply();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadServerData() {
		try {
			SharedPreferences prefs = mConfig.getPrefsPrivate();
			SharedPreferences.Editor edit = prefs.edit();
			int pos1 = serverSpinner.getSelectedItemPosition();
		//	int tun = config.getServersArray().getJSONObject(pos1).getInt("TunnelType");

			String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
			String remote_proxy = config.getServersArray().getJSONObject(pos1).getString("ProxyIP");
			String proxy_port = config.getServersArray().getJSONObject(pos1).getString("ProxyPort");
            //String ssh_user = config.getServersArray().getJSONObject(pos1).getString("ServerUser");
            //String ssh_password = config.getServersArray().getJSONObject(pos1).getString("ServerPass");
			String ssh_port = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
			String ssl_port = config.getServersArray().getJSONObject(pos1).getString("SSLPort");
			String payload = config.getServersArray().getJSONObject(pos1).getString("Payload");
			String sni = config.getServersArray().getJSONObject(pos1).getString("SNI");
			String chaveKey = config.getServersArray().getJSONObject(pos1).getString("Slowchave");
			String serverNameKey = config.getServersArray().getJSONObject(pos1).getString("Nameserver");
			String dnsKey = config.getServersArray().getJSONObject(pos1).getString("Slowdns");
			String token = android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
			
            if (mConfig.getUserOrHwid()){
				mConfig.setUserOrHwid(true);
				edit.putString(Settings.USUARIO_KEY, mConfig.getUserLogin());
				edit.putString(Settings.SENHA_KEY, mConfig.getPasswLogin());
			} else {
				mConfig.setUserOrHwid(false);
				edit.putString(Settings.USUARIO_KEY,token );
				edit.putString(Settings.SENHA_KEY, config.passw);
			}
			edit.putString(Settings.SERVIDOR_KEY, ssh_server);
			edit.putString(Settings.PROXY_IP_KEY, remote_proxy);
			edit.putString(Settings.PROXY_PORTA_KEY, proxy_port);
			
			
			boolean sslType = config.getServersArray().getJSONObject(pos1).getBoolean("isSSL");

			boolean sslpayload = config.getServersArray().getJSONObject(pos1).getBoolean("isPayloadSSL");

			boolean inject = config.getServersArray().getJSONObject(pos1).getBoolean("isInject");

			boolean direct = config.getServersArray().getJSONObject(pos1).getBoolean("isDirect");

			boolean slow = config.getServersArray().getJSONObject(pos1).getBoolean("isSlow");
			
			
            //SSH DIRECT
			if (direct)
			{
				prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT).apply();

				prefs.edit().putString(Settings.SERVIDOR_KEY, ssh_server).apply();
				prefs.edit().putString(Settings.SERVIDOR_PORTA_KEY, ssh_port).apply();

				prefs.edit().putString(Settings.PROXY_IP_KEY, remote_proxy).apply();
				prefs.edit().putString(Settings.PROXY_PORTA_KEY, proxy_port).apply();
				prefs.edit().putString(Settings.CUSTOM_PAYLOAD_KEY, payload).apply();
			}

             //SSH PROXY
			if (inject)
			{
				prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();

				prefs.edit().putString(Settings.SERVIDOR_KEY, ssh_server).apply();
				prefs.edit().putString(Settings.SERVIDOR_PORTA_KEY, ssh_port).apply();

				prefs.edit().putString(Settings.PROXY_IP_KEY, remote_proxy).apply();
				prefs.edit().putString(Settings.PROXY_PORTA_KEY, proxy_port).apply();
				prefs.edit().putString(Settings.CUSTOM_PAYLOAD_KEY, payload).apply();
			}
			
		
            //SSH SSL
			if (sslType)
			{
				prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true).apply();
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSL).apply();

				prefs.edit().putString(Settings.SERVIDOR_KEY, ssh_server).apply();
				prefs.edit().putString(Settings.SERVIDOR_PORTA_KEY, ssl_port).apply();

				prefs.edit().putString(Settings.PROXY_IP_KEY, remote_proxy).apply();
				prefs.edit().putString(Settings.PROXY_PORTA_KEY, proxy_port).apply();

				prefs.edit().putString(Settings.CUSTOM_PAYLOAD_KEY, payload).apply();
				prefs.edit().putString(Settings.CUSTOM_SNI, sni).apply();

			}
			//SSL PAYLOAD
			if (sslpayload)
			{
				prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSL_PAYLOAD).apply();

				prefs.edit().putString(Settings.SERVIDOR_KEY, ssh_server).apply();
				prefs.edit().putString(Settings.SERVIDOR_PORTA_KEY, ssl_port).apply();

				prefs.edit().putString(Settings.CUSTOM_PAYLOAD_KEY, payload).apply();
				prefs.edit().putString(Settings.CUSTOM_SNI, sni).apply();

			}
			
			
			//SLOW DIRECT
			if (slow)
			{

				prefs.edit().putString(Settings.CHAVE_KEY, chaveKey).apply();
				
				prefs.edit().putString(Settings.NAMESERVER_KEY, serverNameKey).apply();
				prefs.edit().putString(Settings.DNS_KEY, dnsKey).apply();

				prefs.edit().putString(Settings.SERVIDOR_KEY, ssh_server).apply();
				prefs.edit().putString(Settings.SERVIDOR_PORTA_KEY, ssh_port).apply();

				prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true).apply();
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SLOWDNS).apply();
			}

			edit.apply();

		} catch (Exception e) {
			SkStatus.logInfo(e.getMessage());
		}
	}

	private void loadServer() {
		try {
			if (serverList.size() > 0) {
				serverList.clear();
				serverAdapter.notifyDataSetChanged();
			}
			for (int i = 0; i < config.getServersArray().length(); i++) {
				JSONObject obj = config.getServersArray().getJSONObject(i);
				serverList.add(obj);
				serverAdapter.notifyDataSetChanged();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateConfig(final boolean isOnCreate) {
		new ConfigUpdate(this, new ConfigUpdate.OnUpdateListener() {
				@Override
				public void onUpdateListener(String result) {
					try {
						if (!result.contains("Error on getting data")) {
							String json_data = AESCrypt.decrypt(config.PASSWORD, result);
							if (isNewVersion(json_data)) {
								newUpdateDialog(result);
							} else {
								if (!isOnCreate) {
									noUpdateDialog();
								}
							}
						} else if(result.contains("Error on getting data") && !isOnCreate){
							errorUpdateDialog(result);
						}
					} catch (Exception e) {
						SkStatus.logInfo(e.getMessage());
					}
				}
			}).start(isOnCreate);
	}

	private boolean isNewVersion(String result) {
		try {
			String current = config.getVersion();
			String update = new JSONObject(result).getString("Version");
			return config.versionCompare(update, current);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void welcomeNotif(){

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
        Notification.Builder notification = new Notification.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(getPackageName() + "Config.json");
            createNotification(notificationManager, getPackageName() + "Config.json");
        }

        notification.setContentTitle(getString(R.string.app_name))
            .setContentText(("Configuracion Actualizada"))
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.zeroicono))
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)
            .setShowWhen(true)
            .setSmallIcon(R.drawable.zeroicono);
        notificationManager.notify(4130,notification.getNotification());
    }

    private void createNotification(NotificationManager notificationManager, String id)
    {
        NotificationChannel mNotif = new NotificationChannel(id, "Config.json", NotificationManager.IMPORTANCE_HIGH);
        mNotif.setShowBadge(true);
        notificationManager.createNotificationChannel(mNotif);
        // TODO: Implement this method
    }

	private void newUpdateDialog(final String result) throws JSONException, GeneralSecurityException{


		String json_data = AESCrypt.decrypt(config.PASSWORD, result);
		String notes = new JSONObject(json_data).getString("ReleaseNotes");
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this); 
		builer.setView(inflate);
		ImageView iv = inflate.findViewById(R.id.icon);
		TextView title = inflate.findViewById(R.id.title);
		TextView ms = inflate.findViewById(R.id.message);
		TextView bubu = inflate.findViewById(R.id.positiveTxt);
		iv.setImageResource(R.drawable.ic_download);
		title.setText("¡Actualización Disponible!");
        ms.setText(notes);
        bubu.setText("Aceptar");
		final AlertDialog alert = builer.create(); 
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //alert.getWindow().setGravity(Gravity.CENTER);
		alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        bubu.setOnClickListener(new View.OnClickListener() { 
		
				@Override 
                public void onClick(View v) { 

                    // TODO: Implement this method
					welcomeNotif();
                    try
                    {
						alert.dismiss();
                        File file = new File(getFilesDir(), "Config.json");
                        OutputStream out = new FileOutputStream(file);
                        out.write(result.getBytes());
                        out.flush();
                        out.close();
                        restart_app();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }});

        alert.show();
    }
		
	private void noUpdateDialog() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this); 
        builer.setView(inflate); 
        ImageView iv = inflate.findViewById(R.id.icon);
		TextView title = inflate.findViewById(R.id.title);
		TextView ms = inflate.findViewById(R.id.message);
		TextView bubu = inflate.findViewById(R.id.positiveTxt);
		iv.setImageResource(R.drawable.src_images_mine_shareicon);
        title.setText("No Hay Actualizacion");
        ms.setText("No se encontro una actualizacion.");
        bubu.setText("Ok");
        final AlertDialog alert = builer.create(); 
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       // alert.getWindow().setGravity(Gravity.CENTER);
		alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        bubu.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View v) { 
                    try
                    {
						alert.dismiss();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }});

        alert.show();
	}

	private void errorUpdateDialog(String error) {
			
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this); 
        builer.setView(inflate); 
        ImageView iv = inflate.findViewById(R.id.icon);
		TextView title = inflate.findViewById(R.id.title);
		TextView ms = inflate.findViewById(R.id.message);
		TextView bubu = inflate.findViewById(R.id.positiveTxt);
		iv.setImageResource(R.drawable.src_images_mine_shareicon);
        title.setText("Error Al Actualizar");
        ms.setText("Ocurrio un error al buscar la actualizacion.\nPor favor contacta al desarrollador.");
        bubu.setText("Ok");
        final AlertDialog alert = builer.create(); 
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //alert.getWindow().setGravity(Gravity.CENTER);
		alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        bubu.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View v) { 
                    try
                    {
						alert.dismiss();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }});

        alert.show();
			
			
	}



	private void restart_app() {
		Context context = SocksHttpApp.getApp();
		PackageManager packageManager = context.getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
		ComponentName componentName = intent.getComponent();
		Intent mainIntent = Intent.makeRestartActivityTask(componentName);
		context.startActivity(mainIntent);
		Runtime.getRuntime().exit(0);
	}

    private void snack(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT).show();
    }
	
	void showSnackBar(String message)
    {
        View customPos = (View) findViewById(R.id.coordinator);
        final Snackbar snac = Snackbar.make(customPos, message, Snackbar.LENGTH_LONG); 
        snac.getView().setBackgroundColor(ContextCompat.getColor(customPos.getContext(), R.color.white));
        snac.setActionTextColor(getResources().getColor(R.color.white));
        customPos = snac.getView(); 
        TextView setTxtGravity = (TextView) customPos.findViewById(com.google.android.material.R.id.snackbar_text);
        setTxtGravity.setTextAlignment(customPos.TEXT_ALIGNMENT_CENTER);
        snac.show(); 
	}
    public void guides()
    {
        new GuideView.Builder(SocksHttpMainActivity.this)
            .setTitle("Servidores")
            .setContentText("Elige el servidor de tu compañia y conecta ")
            .setTargetView(view1)
			.setGravity(Gravity.center)
            .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
            .setGuideListener(new GuideListener() {
                @Override
                public void onDismiss(View view) {
                    //TODO ...
					new GuideView.Builder(SocksHttpMainActivity.this)
                        .setTitle("Estado de la Conexion")
                        .setContentText("Puedes ver el estado de la conexion")
						.setTargetView(view2)
					    .setGravity(Gravity.center)
                        .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
                        .setGuideListener(new GuideListener() {
							@Override
							public void onDismiss(View view) {
								//TODO ...
								new GuideView.Builder(SocksHttpMainActivity.this)
									.setTitle("Registro")
									.setContentText("Aqui puedes ver el registro de la conexion")
									.setTargetView(view3)
									.setGravity(Gravity.center)
									.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
									.setGuideListener(new GuideListener() {
										@Override
										public void onDismiss(View view) {
											//TODO ...
											new GuideView.Builder(SocksHttpMainActivity.this)
												.setTitle("Descarga")
												.setContentText("Datos recibidos de la aplicacion")
												.setTargetView(view5)
												.setGravity(Gravity.center)
												.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
												.setGuideListener(new GuideListener() {
													
													@Override
													public void onDismiss(View view) {
														//TODO ...
														new GuideView.Builder(SocksHttpMainActivity.this)
															.setTitle("Subida")
															.setContentText("Datos subidos por la aplicacion")
															.setTargetView(view6)
															.setGravity(Gravity.center)
															.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
															.setGuideListener(new GuideListener() {
													
													
                                        @Override
                                        public void onDismiss(View view) {
                                            //TODO ...
                                            new GuideView.Builder(SocksHttpMainActivity.this)
                                                .setTitle("Boton para conectar")
                                                .setContentText("Presiona el boton para conectar")                                                                                          
												.setTargetView(view4) 
												.setGravity(Gravity.center)
                                                .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
                                                .setGuideListener(new GuideListener() {

//ari nimu e solod ang bag o


													@Override
													public void onDismiss(View view) {
														//TODO ...

													}
												})
												.build()
									         	.show();

										}
												})
												.build()
									         	.show();

										}

												})
												.build()
									         	.show();

										}
									})
									.build()
									.show();
							}
						})
						.build()
						.show();
				}
			})
			.build()
			.show();
        updatingForDynamicLocationViews();
    }
    private void updatingForDynamicLocationViews() {
        view4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    mGuideView.updateGuideViewLocation();
                }
            });}

    public void offlineUpdate() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE);
    }

    private String importer(Uri uri)
    {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
            reader.close();
        }
        catch (IOException e) {e.printStackTrace();}
        return builder.toString();
    } 

	public void startOrStopTunnel(Activity activity) {
		if (SkStatus.isTunnelActive()) {
            SharedPreferences prefs = mConfig.getPrefsPrivate();
			TunnelManagerHelper.stopSocksHttp(activity);
		}
		else {
			launchVPN();
		}
	}

	private void launchVPN() {
		Intent intent = VpnService.prepare(this);

        if (intent != null) {
            SkStatus.updateStateString("USER_VPN_PERMISSION", "", R.string.state_user_vpn_permission,
									   ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
            // Start the query
            try {
                startActivityForResult(intent, START_VPN_PROFILE);
            } catch (ActivityNotFoundException ane) {
                SkStatus.logError(R.string.no_vpn_support_image);
            }
        } else {
            onActivityResult(START_VPN_PROFILE, Activity.RESULT_OK, null);
        }
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE)
        {
            if (resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    String intentData = importer(uri);
                    //String cipter = AESCrypt.decrypt(ConfigUtil.PASSWORD, intentData);
                    File file = new File(getFilesDir(), "Config.json");
                    OutputStream out = new FileOutputStream(file);
                    out.write(intentData.getBytes());
                    out.flush();
                    out.close();
                    restart_app();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		if (requestCode == START_VPN_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
				SharedPreferences prefs = mConfig.getPrefsPrivate();

				if (!TunnelUtils.isNetworkOnline(this)) {
					Toast.makeText(this, "No Internet Connection", 0).show();
				} else
					TunnelManagerHelper.startSocksHttp(this);
			}
		}}


	public void setStarterButton(Button starterButton, Activity activity) {
		String state = SkStatus.getLastState();
		boolean isRunning = SkStatus.isTunnelActive();

		if (starterButton != null) {
			int resId;

			SharedPreferences prefsPrivate = new Settings(activity).getPrefsPrivate();

			if (ConfigParser.isValidadeExpirou(prefsPrivate
											   .getLong(Settings.CONFIG_VALIDADE_KEY, 0))) {
				resId = R.string.expired;
				starterButton.setEnabled(false);

				if (isRunning) {
					startOrStopTunnel(activity);
				}
			}
			else if (prefsPrivate.getBoolean(Settings.BLOQUEAR_ROOT_KEY, false) &&
					 ConfigParser.isDeviceRooted(activity)) {
				resId = R.string.blocked;
				starterButton.setEnabled(false);

				Toast.makeText(activity, R.string.error_root_detected, Toast.LENGTH_SHORT)
					.show();

				if (isRunning) {
					startOrStopTunnel(activity);
				}
			}
			else if (SkStatus.SSH_INICIANDO.equals(state)) {
				resId = R.string.stop;
				starterButton.setEnabled(false);
			}
			else if (SkStatus.SSH_PARANDO.equals(state)) {
				resId = R.string.state_stopping;
				starterButton.setEnabled(false);
				StatisticGraphData.getStatisticData().getDataTransferStats().stop();
			}
			else {
				resId = isRunning ? R.string.stop : R.string.start;
				starterButton.setEnabled(true);

			}


		}
	}



	@Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

	@Override
	public void onClick(View p1)
	{
		switch (p1.getId()) {
			case R.id.activity_starterButtonMain:
				doSaveData();
				loadServerData();
				startOrStopTunnel(this);
				mShown = false;
				mShown2 = false;
				break;
			case R.id.btnMenu:
				showMenu();
				break;
		}
	}

	void showMenu() {
		PopupMenu popup = new PopupMenu(this, btnMenu);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.main_menu, popup.getMenu());
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem p1)
				{
					switch (p1.getItemId()) {
						case R.id.configUpdate:
							guides();
							break;

						case R.id.item3:
							Intent intentSettings = new Intent(SocksHttpMainActivity.this, ConfigGeralActivity.class);
							//intentSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intentSettings);
							overridePendingTransition(R.anim.up_enter,R.anim.up_exit);
							return true;


						case R.id.miExit:
							showExitDialog();
							break;
						case R.id.tips:
							walangLaman();
							break;
						case R.id.tips1:
							clearz();
							break;

					}
					return true;
				}
			});
		popup.show();
	}

	protected void showBoasVindas() {
		new AlertDialog.Builder(this, R.style.HiroDialog)
            . setTitle(R.string.attention)
            . setMessage(R.string.first_start_msg)
			. setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int p) {
					// ok
				}
			})
			. setCancelable(false)
            . show();
	}

	@Override
	public void updateState(final String state, String msg, int localizedResId, final ConnectionStatus level, Intent intent)
	{
		mHandler.post(new Runnable() {
				@Override
				public void run() {
					doUpdateLayout();
					if(SkStatus.isTunnelActive()){  
						if(level.equals(ConnectionStatus.LEVEL_CONNECTED)) {
							if (!mShown){
								toastutil.showSuccessToast("Conectado");
								mShown = true;
							}
							status.setText(R.string.state_connected);
                            status.setTextColor(Color.parseColor("#5cb85d"));
                            rippleBackground.stopRippleAnimation();
                            //rippleBackground.setRippleColor(Color.parseColor(getString(R.color.colorConnected)));    *baner
                            rippleBackground.startRippleAnimation();
							
							if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
								bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
							}
							
						/* ImageView bg = (ImageView)findViewById(R.id.kantot);
							bg.setImageResource(R.drawable.antenna_3);*/
							 
						}
						if(level.equals(ConnectionStatus.LEVEL_NOTCONNECTED)){

							status.setText(R.string.state_disconnected);
							//rippleBackground.setRippleColor(Color.parseColor(getString(R.color.colorConnected)));   *baner color creo
                            rippleBackground.startRippleAnimation();		
						}
						if(level.equals(ConnectionStatus.LEVEL_CONNECTING_SERVER_REPLIED)){
							status.setText( R.string.state_auth);
							
							/*ImageView bg = (ImageView)findViewById(R.id.kantot);
							bg.setImageResource(R.drawable.antenna_2);*/
							
						}
						if(level.equals(ConnectionStatus.LEVEL_CONNECTING_NO_SERVER_REPLY_YET)){
							status.setText(R.string.state_connecting);
							status.setTextColor(Color.parseColor("#ffffff"));     //esto es para el color del boton de que dice conectado autentificado etc neko
							

							if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
								bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
							}
							
							
						/*	ImageView bg = (ImageView)findViewById(R.id.kantot);
							bg.setImageResource(R.drawable.antenna_1);*/
							
						}
						if(level.equals(ConnectionStatus.UNKNOWN_LEVEL)){
							if (!mShown2){
								toastutil.showErrorToast("Desconectado");
								mShown2 = true;
							}		
							status.setText(R.string.state_disconnected);
                            status.setTextColor(Color.parseColor("#FFFF0000"));
							//rippleBackground.setRippleColor(Color.parseColor(getString(R.color.colorConnected)));   * color baner
                            rippleBackground.startRippleAnimation();		
							
							
							
							if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
								bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
						
							}
						/**	ImageView bg = (ImageView)findViewById(R.id.kantot);
							bg.setImageResource(R.drawable.antenna_disconnect);*/
							
							//bg.setImageTintList(getResources().getColorStateList(R.color.colorOpposite));
							
						}

					}
					if(level.equals(ConnectionStatus.LEVEL_NONETWORK)){
						status.setText(R.string.state_nonetwork);
					}
					if(level.equals(ConnectionStatus.LEVEL_AUTH_FAILED)){
						status.setText(R.string.state_auth_failed);
					}

				}
			});

		switch (state) {
			case SkStatus.SSH_CONECTADO:
				// carrega ads banner
				if (adsBannerView != null && TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
					adsBannerView.setAdListener(new AdListener() {
							@Override
							public void onAdLoaded() {
								if (adsBannerView != null && !isFinishing()) {
									adsBannerView.setVisibility(View.VISIBLE);
								}
							}
						});
					adsBannerView.postDelayed(new Runnable() {
							@Override
							public void run() {
								// carrega ads interestitial
								AdsManager.newInstance(getApplicationContext())
									.loadAdsInterstitial();
								// ads banner
								if (adsBannerView != null && !isFinishing()) {
									adsBannerView.loadAd(new AdRequest.Builder()
														 .build());
								}
							}
						}, 5000);
				}
				break;
		}
	}

//UnityAds


	//End UnityAds
	/**
	 * Recebe locais Broadcast
	 */

	private BroadcastReceiver mActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null)
                return;

            if (action.equals(UPDATE_VIEWS) && !isFinishing()) {
				doUpdateLayout();
			}
        }
    };


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
		// Obtain the FirebaseAnalytics instance.
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.configUpdate:

				updateConfig(false);
				break;
			case R.id.miExit:
				if (Build.VERSION.SDK_INT >= 16) {
					finishAffinity();
				}

				System.exit(0);
				break;
		}

		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed() {
			// mostra opção para sair
		AlertDialog dialog = new AlertDialog.Builder(this, R.style.HiroDialog).
			create();
		dialog.setTitle(getString(R.string.attention));
		dialog.setMessage(getString(R.string.alert_exit));

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.
																	string.exit),
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Utils.exitAll(SocksHttpMainActivity.this);
				}
			}
		);

		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.
																	string.minimize),
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// minimiza app
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(startMain);
				}
			}
		);
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Cancelar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					p1.dismiss();
				}
			});
		dialog.show();
	}

    public void settings(View v)
    {
        Intent intentSettings = new Intent(SocksHttpMainActivity.this, ConfigGeralActivity.class);
        //intentSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentSettings);
    }


	@Override
    public void onResume() {
        super.onResume();
		SharedPreferences sPrefs = mConfig.getPrefsPrivate();
        int server = sPrefs.getInt("LastSelectedServer", 0);
		serverSpinner.setSelection(server);


		rippleBackground.startRippleAnimation();		

        if (adsBannerView != null) {
            adsBannerView.resume();
        }
		SkStatus.addStateListener(this);
		if (adsBannerView != null) {
			adsBannerView.resume();
		}
		new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								updateHeaderCallback();
							}
						});
				}
			}, 0,1000);
    }

	@Override
	protected void onPause()
	{
		super.onPause();
		doSaveData();
		SkStatus.removeStateListener(this);
		if (adsBannerView != null) {
			adsBannerView.pause();
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

		SharedPreferences prefs = mConfig.getPrefsPrivate();
        SharedPreferences.Editor edit = prefs.edit();
        int server = serverSpinner.getSelectedItemPosition();
        edit.putInt("LastSelectedServer", server);
        edit.apply();

		if (adsBannerView != null) {
			adsBannerView.destroy();
		}
	}

	public static void updateMainViews(Context context) {
		Intent updateView = new Intent(UPDATE_VIEWS);
		LocalBroadcastManager.getInstance(context)
			.sendBroadcast(updateView);
	}

	private void walangLaman()
	{
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this); 
        builer.setView(inflate); 
        ImageView iv = inflate.findViewById(R.id.icon);
		TextView title = inflate.findViewById(R.id.title);
		TextView ms = inflate.findViewById(R.id.message);
		TextView bubu = inflate.findViewById(R.id.positiveTxt);
		iv.setImageResource(R.drawable.ic_help_circle);
        title.setText("Ayuda");
        ms.setText("Si está conectado  pero no tiene conexión de datos a Internet, vaya al menú, configuración y habilite el reenviador de DNS, luego intente conectarse\n\nSi habilitó el reenviador de DNS todavía sin conexión de datos de Internet, intente nuevamente deshabilitar el reenviador de DNS y comience a conectarse");
        bubu.setText("Entendido");
        final AlertDialog alert = builer.create(); 
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //alert.getWindow().setGravity(Gravity.CENTER);     * choca con notificacion y guia
		alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        bubu.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View v) { 
                    try
                    {
						alert.dismiss();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }});

        alert.show();
   
	}


	private void about(){
        Intent aboutIntent = new Intent(this, AboutActivity.class);
		startActivity(aboutIntent);
    }

    private void Changelogs(){	
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.help, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this); 
        builer.setView(inflate); 
        ImageView iv = inflate.findViewById(R.id.icon);
		TextView title = inflate.findViewById(R.id.title);
		TextView ms = inflate.findViewById(R.id.message);
		TextView bubu = inflate.findViewById(R.id.positiveTxt);
		iv.setImageResource(R.drawable.ic_bell_ring);
        title.setText("Notificaciones");
        ms.setText(this.config.geNote());
        bubu.setText("Aceptar");
        final AlertDialog alert = builer.create(); 
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //alert.getWindow().setGravity(Gravity.CENTER);
		alert.getWindow().getAttributes().windowAnimations = R.style.dialog;
        alert.show();
        bubu.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public void onClick(View v) { 
                    try
                    {
						alert.dismiss();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }});

        alert.show();
    }
	private void clearz()
	{
		nops = new SweetAlertDialog(SocksHttpMainActivity.this, SweetAlertDialog.WARNING_TYPE);
		nops.setTitleText("Advertencia !!");
		nops.setContentText("¿Está seguro de borrar los datos de la aplicación , incluidas las actualizaciones de configuración? Haga clic en Aceptar para continuar");
		nops.setCancelText("Cancelar");
		nops.setConfirmText("Aceptar");
		nops.showCancelButton(true);
		nops.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
				@Override
				public void onClick(SweetAlertDialog sDialog) {
					nops.cancel();
				}
			});
		nops.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
				@Override
				public void onClick(SweetAlertDialog sDialog) {
					try {
						// clearing app data
						String packageName = getApplicationContext().getPackageName();
						Runtime runtime = Runtime.getRuntime();
						runtime.exec("pm clear "+packageName);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		nops.show();
	}

	public void Changelogs1(View v)
	{
        this.nops =  new SweetAlertDialog(SocksHttpMainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        this.nops.setTitleText("Message");
        this.nops.setContentText(this.config.geNote());
        this.nops.show();

    }


    public void offlinez(View v)
    {
        drawerLayout.openDrawer(GravityCompat.START);
	}

	public void showExitDialog() {
		AlertDialog dialog = new AlertDialog.Builder(this, R.style.HiroDialog).
			create();
		dialog.setTitle(getString(R.string.attention));
		dialog.setMessage(getString(R.string.alert_exit));

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.
																	string.exit),
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Utils.exitAll(SocksHttpMainActivity.this);
				}
			}
		);

		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.
																	string.minimize),
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// minimiza app
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(startMain);
				}
			}
		);
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Cancelar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					p1.dismiss();
				}
			});
		dialog.show();
        
        
	}
}

