package net.metaquotes.mt5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import androidx.core.view.GravityCompat;
import android.view.*;
import android.widget.*;
import android.graphics.Color;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.io.*;
import android.os.Environment;
import android.os.Build;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
	DrawerLayout drawerLayout;
	NavigationView navigationView;
	ImageView drawerToggle;
	LinearLayout tradeLayout;
	LinearLayout settingsLayout;
	TextView layoutTitle;
	LinearLayout manageAccounts;
	ImageView icon1;
	ImageView icon2;
	ImageView icon3;
	RelativeLayout accountsLayout;
	RelativeLayout tradeRelativeLayout;
	LinearLayout History;
	LinearLayout Trade;
	ImageView HistoryImg;
	ImageView TradeImg;
	TextView HistoryTxt;
	TextView TradeTxt;
	LinearLayout historyLayout;
	TextView positionsTxt;
	TextView ordersTxt;
	TextView dealsTxt;
	View positionsLine;
	View ordersLine;
	View dealsLine;
	LinearLayout positionsLayout;
	LinearLayout ordersLayout;
	LinearLayout dealsLayout;
	RelativeLayout historyPositionsLayout;
	RelativeLayout historyOrdersLayout;
	TextView profitTxt;
	TextView depositTxt;
	TextView withdrawalTxt;
	TextView swapTxt;
	TextView commissionTxt;
	File dataDir;
	TextView[][] trades;
	View[] bars;
	View[] divs;
	File[] tradeFiles;
	RelativeLayout[] tradeLayouts;
	TextView balanceTime;
	TextView balanceId;
	TextView balance1;
	File balanceFile;
	RelativeLayout balanceLayout;
	View addTrade;
	TextView Asad1;
	TextView Exness1;
	TextView Exness2;
	TextView Exness3;
	TextView Usd;
	TextView Asad;
	TextView mt5Real;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		drawerLayout = findViewById(R.id.drawer_layout);
		navigationView = findViewById(R.id.navigation_view);
		drawerToggle = findViewById(R.id.drawer_toggle);
		tradeLayout = findViewById(R.id.trade_layout);
		settingsLayout = findViewById(R.id.settings_layout);
		layoutTitle = findViewById(R.id.layout_title);
		manageAccounts = findViewById(R.id.manage_accounts);
		icon1 = findViewById(R.id.icon_1);
		icon2 = findViewById(R.id.icon_2);
		icon3 = findViewById(R.id.icon_3);
		accountsLayout = findViewById(R.id.accounts_layout);
		tradeRelativeLayout = findViewById(R.id.trade_relative_layout);
		History = findViewById(R.id.history);
		Trade = findViewById(R.id.trade2);
		HistoryImg = findViewById(R.id.history_img);
		TradeImg = findViewById(R.id.trade_img);
		HistoryTxt = findViewById(R.id.history_txt);
		TradeTxt = findViewById(R.id.trade_txt);
		historyLayout = findViewById(R.id.history_layout);
		positionsTxt = findViewById(R.id.positions_txt);
		ordersTxt = findViewById(R.id.orders_txt);
		dealsTxt = findViewById(R.id.deals_txt);
		positionsLine = findViewById(R.id.positions_line);
		ordersLine = findViewById(R.id.orders_line);
		dealsLine = findViewById(R.id.deals_line);
		positionsLayout = findViewById(R.id.positions_layout);
		ordersLayout = findViewById(R.id.orders_layout);
		dealsLayout = findViewById(R.id.deals_layout);
		historyPositionsLayout = findViewById(R.id.history_positions_layout);
		historyOrdersLayout = findViewById(R.id.history_orders_layout);
		profitTxt = findViewById(R.id.profit_txt);
		depositTxt = findViewById(R.id.deposit_txt);
		withdrawalTxt = findViewById(R.id.withdrawal_txt);
		swapTxt = findViewById(R.id.swap_txt);
		commissionTxt = findViewById(R.id.commission_txt);
		balanceTime = findViewById(R.id.balance_time);
		balanceId = findViewById(R.id.balance_id);
		balance1 = findViewById(R.id.balance_1);
		balanceLayout = findViewById(R.id.balance_layout);
		addTrade = findViewById(R.id.add_trade);
		Asad1 = findViewById(R.id.asad1);
		Exness1 = findViewById(R.id.exness1);
		Exness2 = findViewById(R.id.exness2);
		Exness3 = findViewById(R.id.exness3);
		Usd = findViewById(R.id.usd);
		Asad = findViewById(R.id.asad);
		mt5Real = findViewById(R.id.mt5real);
		
		dataDir = (Build.VERSION.SDK_INT<=29)? (new File(Environment.getExternalStorageDirectory(), "mt5")) : getExternalFilesDir(null);
		File profitFile = new File(dataDir, "profit");
		File depositFile = new File(dataDir, "deposit");
		File withdrawalFile = new File(dataDir, "withdrawal");
		File swapFile = new File(dataDir, "swap");
		File commissionFile = new File(dataDir, "commission");
		File asad1File = new File(dataDir, "asad1");
		File exness1File = new File(dataDir, "exness1");
		File exness2File = new File(dataDir, "exness2");
		File exness3File = new File(dataDir, "exness3");
		File usdFile = new File(dataDir, "usd");
		File asadFile = new File(dataDir, "asad");
		File mt5RealFile = new File(dataDir, "mt5real");
		balanceFile = new File(dataDir, "balance1");
		tradeLayouts = new RelativeLayout[10];
		trades = new TextView[10][5];
		divs = new View[10];
		bars = new View[10];
		String[] textViewNames = new String[]{"share1", "quantity1", "transaction1_time", "transaction1", "transaction1_amount"};
		for(int i=0;i<10;i++)
		{
			tradeLayouts[i] = findViewById(getResources().getIdentifier("trade"+String.valueOf(i+1)+"_layout", "id", getPackageName()));
			for(int j=0;j<5;j++)
			{
				trades[i][j] = findViewById(getResources().getIdentifier(textViewNames[j].replace("1", String.valueOf(i+1)), "id", getPackageName()));
			}
			bars[i] = findViewById(getResources().getIdentifier("transaction"+String.valueOf(i+1)+"_bar", "id", getPackageName()));
			divs[i] = findViewById(getResources().getIdentifier("transaction"+String.valueOf(i+1)+"_div", "id", getPackageName()));
		}
		try{
		if(!balanceFile.exists())
		{
			balanceFile.createNewFile();writeToFile(balanceFile, new String[]{balanceTime.getText().toString(), balanceId.getText().toString(), balance1.getText().toString()});
		}else{
			String[] values = readFromFile(balanceFile, "SEPARATOR_NEW_LINE");
			balanceTime.setText(values[0]);
			balanceId.setText(values[1]);
			balance1.setText(values[2]);
		}
		}catch(IOException e){}
		
		tradeFiles = new File[10];
		for(int i=0;i<10;i++)
		{try{
			tradeFiles[i] = new File(dataDir, "trade"+String.valueOf(i+1));
			if(!tradeFiles[i].exists())
			{
					tradeFiles[i].createNewFile();
					writeToFile(tradeFiles[i], new String[]{trades[i][0].getText().toString(), trades[i][1].getText().toString(), trades[i][2].getText().toString(), trades[i][3].getText().toString(), trades[i][4].getText().toString(), String.valueOf(View.VISIBLE), "Red Bar 🔽"});
			}else{
				String[] values = readFromFile(tradeFiles[i], "SEPARATOR_NEW_LINE");
				for(int j=0;j<5;j++)
				{
					trades[i][j].setText(values[j]);
					trades[i][j].setVisibility(Integer.parseInt(values[5]));
				}
				divs[i].setVisibility(Integer.parseInt(values[5]));
							switch(values[6])
								{
										case "Red Bar 🔽" : bars[i].setBackgroundColor(Color.parseColor("#eb7018"));break;
										case "Green Bar 🔽" : bars[i].setBackgroundColor(Color.parseColor("#3CB043"));break;
										case "White Bar 🔽" : bars[i].setBackgroundColor(Color.parseColor("#ffffff"));break;
								}
			}
		}catch(IOException e){}}
		
		if(!dataDir.exists())
			dataDir.mkdir();
		else
		{	
			try{
				if(profitFile.exists())
					profitTxt.setText(readFromFile(profitFile));
				if(depositFile.exists())
					depositTxt.setText(readFromFile(depositFile));
				if(withdrawalFile.exists())
					withdrawalTxt.setText(readFromFile(withdrawalFile));
				if(swapFile.exists())
					swapTxt.setText(readFromFile(swapFile));
				if(commissionFile.exists())
					commissionTxt.setText(readFromFile(commissionFile));
				if(asad1File.exists())
					Asad1.setText(readFromFile(asad1File));
				if(exness1File.exists())
					Exness1.setText(readFromFile(exness1File));
				if(exness2File.exists())
					Exness2.setText(readFromFile(exness2File));
				if(exness3File.exists())
					Exness3.setText(readFromFile(exness3File));
				if(usdFile.exists())
					Usd.setText(readFromFile(usdFile));
				if(asadFile.exists())
					Asad.setText(readFromFile(asadFile));
				if(mt5RealFile.exists())
					mt5Real.setText(readFromFile(mt5RealFile));
				
				updateColors();
			}catch(IOException e){}
		}
		
		if(getSupportActionBar()!=null)
			getSupportActionBar().hide();
		
		tradeLayout.setBackgroundColor(Color.parseColor("#f8f8f8"));
		
		drawerToggle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					drawerLayout.openDrawer(Gravity.LEFT); //OPEN Nav Drawer!	
				Asad.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(Asad, asadFile);
						return true;
					}
				});	
				mt5Real.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(mt5Real, mt5RealFile);
						return true;
					}
				});
			}
		});
		
		tradeLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HistoryImg.setImageResource(R.drawable.l);
				TradeImg.setImageResource(R.drawable.cz1);
				HistoryTxt.setTextColor(Color.parseColor("#000000"));
				TradeTxt.setTextColor(Color.parseColor("#3183ff"));
				tradeLayout.setBackgroundColor(Color.parseColor("#f8f8f8"));
				settingsLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				layoutTitle.setText("Trade");
				icon1.setImageResource(R.drawable.ey);
				icon2.setImageResource(R.drawable.vd);
				icon3.setImageResource(R.drawable.cw);
				icon1.setVisibility(View.VISIBLE);				
				icon2.setVisibility(View.VISIBLE);
				icon3.setVisibility(View.VISIBLE);
				accountsLayout.setVisibility(View.GONE);
				tradeRelativeLayout.setVisibility(View.VISIBLE);
				historyLayout.setVisibility(View.GONE);
				addTrade.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {}
				});
				drawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		settingsLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HistoryImg.setImageResource(R.drawable.l);
				TradeImg.setImageResource(R.drawable.cz);
				HistoryTxt.setTextColor(Color.parseColor("#000000"));
				TradeTxt.setTextColor(Color.parseColor("#000000"));
				tradeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				settingsLayout.setBackgroundColor(Color.parseColor("#f8f8f8"));
				layoutTitle.setText("Settings");
				icon1.setVisibility(View.GONE);				
				icon2.setVisibility(View.GONE);
				icon3.setVisibility(View.GONE);
				accountsLayout.setVisibility(View.GONE);
				tradeRelativeLayout.setVisibility(View.GONE);
				historyLayout.setVisibility(View.GONE);
				drawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		manageAccounts.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HistoryImg.setImageResource(R.drawable.l);
				TradeImg.setImageResource(R.drawable.cz);
				HistoryTxt.setTextColor(Color.parseColor("#000000"));
				TradeTxt.setTextColor(Color.parseColor("#000000"));
				tradeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				settingsLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				layoutTitle.setText("Accounts");
				icon1.setImageResource(R.drawable.pa);
				icon2.setImageResource(R.drawable.e9);
				icon3.setImageResource(R.drawable.qz);
				icon1.setVisibility(View.VISIBLE);		
				icon2.setVisibility(View.VISIBLE);
				icon3.setVisibility(View.VISIBLE);
				accountsLayout.setVisibility(View.VISIBLE);
				tradeRelativeLayout.setVisibility(View.GONE);
				historyLayout.setVisibility(View.GONE);
				addTrade.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {}
				});	
				Asad1.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(Asad1, asad1File);
						return true;
					}
				});	
				Exness1.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(Exness1, exness1File);
						return true;
					}
				});
				Exness2.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(Exness2, exness2File);
						return true;
					}
				});
				Exness3.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(Exness3, exness3File);
						return true;
					}
				});
				Usd.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(Usd, usdFile);
						return true;
					}
				});
				drawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		History.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{HistoryImg.setImageResource(R.drawable.l11);
				TradeImg.setImageResource(R.drawable.cz);
				HistoryTxt.setTextColor(Color.parseColor("#3183ff"));
				TradeTxt.setTextColor(Color.parseColor("#000000"));
				tradeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				settingsLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				layoutTitle.setText("History");
				icon1.setImageResource(R.drawable.y7);
				icon2.setImageResource(R.drawable.vd);
				icon3.setImageResource(R.drawable.fb1);
				icon1.setVisibility(View.VISIBLE);				
				icon2.setVisibility(View.VISIBLE);
				icon3.setVisibility(View.VISIBLE);
				accountsLayout.setVisibility(View.GONE);
				tradeRelativeLayout.setVisibility(View.GONE);
				historyLayout.setVisibility(View.VISIBLE);
				
				positionsLayout.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						positionsTxt.setTextColor(Color.parseColor("#000000"));
						positionsLine.setVisibility(View.VISIBLE);
						ordersTxt.setTextColor(Color.parseColor("#818181"));
						ordersLine.setVisibility(View.INVISIBLE);
						dealsTxt.setTextColor(Color.parseColor("#818181"));
						dealsLine.setVisibility(View.INVISIBLE);
						historyPositionsLayout.setVisibility(View.VISIBLE);
						historyOrdersLayout.setVisibility(View.GONE);
					}
				});
				
				ordersLayout.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ordersTxt.setTextColor(Color.parseColor("#000000"));
						ordersLine.setVisibility(View.VISIBLE);
						positionsTxt.setTextColor(Color.parseColor("#818181"));
						positionsLine.setVisibility(View.INVISIBLE);
						dealsTxt.setTextColor(Color.parseColor("#818181"));
						dealsLine.setVisibility(View.INVISIBLE);
						historyPositionsLayout.setVisibility(View.GONE);
						historyOrdersLayout.setVisibility(View.VISIBLE);
					}
				});
				
				dealsLayout.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dealsTxt.setTextColor(Color.parseColor("#000000"));
						dealsLine.setVisibility(View.VISIBLE);
						ordersTxt.setTextColor(Color.parseColor("#818181"));
						ordersLine.setVisibility(View.INVISIBLE);
						positionsTxt.setTextColor(Color.parseColor("#818181"));
						positionsLine.setVisibility(View.INVISIBLE);
						historyPositionsLayout.setVisibility(View.VISIBLE);
						historyOrdersLayout.setVisibility(View.GONE);
					}
				});
						
				profitTxt.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(profitTxt, profitFile);
						return true;
					}
				});
				depositTxt.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(depositTxt, depositFile);
						return true;
					}
				});
				withdrawalTxt.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(withdrawalTxt, withdrawalFile);
						return true;
					}
				});
				swapTxt.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(swapTxt, swapFile);
						return true;
					}
				});
				commissionTxt.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						changeText(commissionTxt, commissionFile);
						return true;
					}
				});
				
				balanceLayout.setOnLongClickListener(new View.OnLongClickListener() {
						
						@Override
						public boolean onLongClick(View v)
						{
							LinearLayout linearLayout = new LinearLayout(getApplicationContext());
							linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
							linearLayout.setOrientation(LinearLayout.VERTICAL);
							EditText balanceTimeEdit = new EditText(getApplicationContext());
							balanceTimeEdit.setText(balanceTime.getText().toString());
							linearLayout.addView(balanceTimeEdit);
							EditText balanceIdEdit = new EditText(getApplicationContext());
							balanceIdEdit.setText(balanceId.getText().toString());
							linearLayout.addView(balanceIdEdit);
							EditText balance1Edit = new EditText(getApplicationContext());
							balance1Edit.setText(balance1.getText().toString());
							linearLayout.addView(balance1Edit);
							
							AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
									.setView(linearLayout)
									.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											balanceTime.setText(balanceTimeEdit.getText().toString());
											balanceId.setText(balanceIdEdit.getText().toString());
											balance1.setText(balance1Edit.getText().toString());
											updateColors();
											try{
												balanceFile.delete();balanceFile.createNewFile();writeToFile(balanceFile, new String[]{balanceTime.getText().toString(), balanceId.getText().toString(), balance1.getText().toString()});
											}catch(IOException e){}
										}
									})
									.setNegativeButton("Cancel", null)
									.create();
							dialog.show();
							
							return true;
						}
				});
					
				for(int i=0;i<10;i++)
				{
					final int i1 = i;
					tradeLayouts[i].setOnLongClickListener(new View.OnLongClickListener() {
						
						@Override
						public boolean onLongClick(View v)
						{
							LinearLayout linearLayout = new LinearLayout(getApplicationContext());
							linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
							linearLayout.setOrientation(LinearLayout.VERTICAL);
							EditText[] editTexts = new EditText[5];
							for(int j=0;j<5;j++)
							{
								editTexts[j] = new EditText(getApplicationContext());
								editTexts[j].setText(trades[i1][j].getText().toString());
								linearLayout.addView(editTexts[j]);
							}
							
							Button button = new Button(getApplicationContext());
							button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
							try{String[] values = readFromFile(tradeFiles[i1], "SEPARATOR_NEW_LINE");
								button.setText(values[6]);
								}catch(IOException e){}
							linearLayout.addView(button);
							button.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									PopupMenu popupMenu = new PopupMenu(MainActivity.this, button);
									popupMenu.getMenu().add("Red");
									popupMenu.getMenu().add("Green");
									popupMenu.getMenu().add("White");
									popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
										@Override
										public boolean onMenuItemClick(MenuItem item) {
											switch (item.getTitle().toString()) {
												case "Red" :
													//execute "edit" action
													button.setText("Red Bar 🔽");
													break;
												case "Green" :
													//execute "delete" action
													button.setText("Green Bar 🔽");
													break;
												case "White" :
													//execute "delete" action
													button.setText("White Bar 🔽");
													break;
											}
											return false;
										}
									});
									popupMenu.show();
								}
							});
							CheckBox checkBox = new CheckBox(getApplicationContext());
							checkBox.setText("Visible");
							checkBox.setChecked(divs[i1].getVisibility()==View.VISIBLE?true : false);
							linearLayout.addView(checkBox);

							AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
									.setView(linearLayout)
									.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											for(int j=0;j<5;j++)
											{
												trades[i1][j].setText(editTexts[j].getText().toString());
												if(!checkBox.isChecked())
													trades[i1][j].setVisibility(View.GONE);
											}
											updateColors();
											if(!checkBox.isChecked())
													divs[i1].setVisibility(View.GONE);
											switch(button.getText().toString())
											{
													case "Red Bar 🔽" : bars[i1].setBackgroundColor(Color.parseColor("#eb7018"));break;
													case "Green Bar 🔽" : bars[i1].setBackgroundColor(Color.parseColor("#3CB043"));break;
													case "White Bar 🔽" : bars[i1].setBackgroundColor(Color.parseColor("#ffffff"));break;
											}
											try{
												tradeFiles[i1].delete();tradeFiles[i1].createNewFile();writeToFile(tradeFiles[i1], new String[]{trades[i1][0].getText().toString(), trades[i1][1].getText().toString(), trades[i1][2].getText().toString(), trades[i1][3].getText().toString(), trades[i1][4].getText().toString(), String.valueOf(checkBox.isChecked()?View.VISIBLE : View.GONE), button.getText().toString()});
											}catch(IOException e){}
										}
									})
									.setNegativeButton("Cancel", null)
									.create();
							dialog.show();
							
							return true;
						}
					});
				}
				
				addTrade.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						for(int i=0;i<10;i++)
						{
							if(divs[i].getVisibility()==View.GONE)
							{
								for(int j=0;j<5;j++)
									trades[i][j].setVisibility(View.VISIBLE);
								divs[i].setVisibility(View.VISIBLE);
								try{
									tradeFiles[i].delete();tradeFiles[i].createNewFile();writeToFile(tradeFiles[i], new String[]{trades[i][0].getText().toString(), trades[i][1].getText().toString(), trades[i][2].getText().toString(), trades[i][3].getText().toString(), trades[i][4].getText().toString(), String.valueOf(View.VISIBLE), "Red Bar 🔽"});
								}catch(IOException e){}
								break;
							}
						}
					}
				});
				}catch(Exception e){Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();}
			}
		});
		Trade.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HistoryImg.setImageResource(R.drawable.l);
				TradeImg.setImageResource(R.drawable.cz1);
				HistoryTxt.setTextColor(Color.parseColor("#000000"));
				TradeTxt.setTextColor(Color.parseColor("#3183ff"));
				tradeLayout.setBackgroundColor(Color.parseColor("#f8f8f8"));
				settingsLayout.setBackgroundColor(Color.parseColor("#ffffff"));
				layoutTitle.setText("Trade");
				icon1.setImageResource(R.drawable.ey);
				icon2.setImageResource(R.drawable.vd);
				icon3.setImageResource(R.drawable.cw);
				icon1.setVisibility(View.VISIBLE);				
				icon2.setVisibility(View.VISIBLE);
				icon3.setVisibility(View.VISIBLE);
				accountsLayout.setVisibility(View.GONE);
				tradeRelativeLayout.setVisibility(View.VISIBLE);
				addTrade.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {}
				});
				historyLayout.setVisibility(View.GONE);
			}
		});
	}
	
	private void changeText(TextView textView, File txtFile)
	{
		final EditText editText = new EditText(this);
		editText.setText(textView.getText());
		AlertDialog dialog = new AlertDialog.Builder(this)
				.setView(editText)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						textView.setText(editText.getText().toString());
						try{
						if(txtFile.exists())
							txtFile.delete();
						txtFile.createNewFile();writeToFile(txtFile, new String[]{editText.getText().toString()});
						updateColors();
						}catch(IOException e){}
					}
				})
				.setNegativeButton("Cancel", null)
				.create();
		dialog.show();
	}
	
	private void updateColors()
	{
					if(Float.parseFloat(profitTxt.getText().toString().replace(" ", ""))<0.00)
						profitTxt.setTextColor(Color.parseColor("#d80000"));
					else
						profitTxt.setTextColor(Color.parseColor("#0b71f3"));
					if(Float.parseFloat(balance1.getText().toString())<0.00)
						balance1.setTextColor(Color.parseColor("#d80000"));
					else
						balance1.setTextColor(Color.parseColor("#0b71f3"));
					for(int i=0;i<10;i++)
					{
						if(Float.parseFloat(trades[i][4].getText().toString())<0.00)
							trades[i][4].setTextColor(Color.parseColor("#d80000"));
						else
							trades[i][4].setTextColor(Color.parseColor("#0b71f3"));
						
						if(trades[i][1].getText().toString().toLowerCase().contains("sell"))
							trades[i][1].setTextColor(Color.parseColor("#d80000"));
						else
							trades[i][1].setTextColor(Color.parseColor("#0b71f3"));
					}
	}
	
	public boolean writeToFile(File file, String[] data) throws IOException
	{
		FileOutputStream outputStream = new FileOutputStream(file, file.exists());
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		for(String dt : data)
		{
			bufferedWriter.write(dt);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		return true;
	}

	public String readFromFile(File file) throws IOException
	{
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String line = "";
			line = bufferedReader.readLine();
			bufferedReader.close();

			return line;
	}
	
	public String[] readFromFile(File file, String SEPARATOR) throws IOException
	{
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		if(SEPARATOR.equals("SEPARATOR_NEW_LINE"))
		{
			ArrayList data = new ArrayList<String>();
			String line = "";
			while((line = bufferedReader.readLine()) != null)
				data.add(line);

			bufferedReader.close();

			return (String[])data.toArray(new String[]{});

		}
		else
		{
			String data = "";
			String line = "";
			while((line = bufferedReader.readLine()) != null)
				data += line;

			bufferedReader.close();

			return data.split(SEPARATOR);
		}
	}

}