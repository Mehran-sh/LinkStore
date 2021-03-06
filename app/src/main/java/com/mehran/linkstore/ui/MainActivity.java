package com.mehran.linkstore.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.URLUtil;

import com.mehran.linkstore.R;
import com.mehran.linkstore.ui.fragments.LinksFragment;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = getUrlFromIntent();
        if(url == null)
        {
            url = getUrlFromClipBoard();
        }
        openHomeFragment(url);
    }

    private String getUrlFromIntent()
    {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(Intent.ACTION_SEND.equalsIgnoreCase(action))
        {
            if ("text/plain".equals(type))
            {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if(URLUtil.isValidUrl(sharedText))
                {
                    return sharedText;
                }
                else
                {
                    return searchForUrlInText(sharedText);
                }
            }
        }

        return null;
    }

    private String searchForUrlInText(String text)
    {
        String result = text;

        try
        {
            int startIndex = result.toLowerCase().indexOf("http");
            result = result.substring(startIndex);
            int endIndex = result.indexOf(" ");
            if(endIndex != -1)
            {
                result = result.substring(0, endIndex);
            }
        }
        catch (Exception e)
        {
            result = "";
        }

        return result;
    }

    private String getUrlFromClipBoard()
    {
        String result = "";
        try
        {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            String pasteData = "";
            if(clipboard.getPrimaryClipDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))
            {
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                pasteData = item.getText().toString();
                result = searchForUrlInText(pasteData);
            }
            else
            {
                result = null;
            }
        }
        catch (Exception e)
        {
            result = "";
        }

        return result;
    }

    private void openHomeFragment(String url)
    {
        LinksFragment fragment = LinksFragment.getInstance(url);

        openFragment(fragment);
    }

    private void openFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.content_main, fragment ,fragment.getClass().getSimpleName());
        transaction.commit();
    }
}
