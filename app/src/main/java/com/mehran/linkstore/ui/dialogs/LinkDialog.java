package com.mehran.linkstore.ui.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mehran.linkstore.R;
import com.mehran.linkstore.data.models.Link;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mehran on 21.08.17.
 */

public class LinkDialog extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.txtUrl)
    EditText txtUrl;
    @BindView(R.id.chkImportant)
    CheckBox chkImportant;
    @BindView(R.id.btnSaveLink)
    Button btnSave;
    @BindView(R.id.btnCancelSaveLink)
    Button btnCancel;

    Link currentLink;

    public static LinkDialog getInstance(Link link)
    {
        LinkDialog linkDialog = new LinkDialog();
        Bundle args = new Bundle();
        args.putParcelable(Link.KEY, link);
        linkDialog.setArguments(args);
        return linkDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_link, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if(args.containsKey(Link.KEY))
        {
            currentLink = args.getParcelable(Link.KEY);
        }
        else
        {
            currentLink = null;
        }
    }

    private void initUi()
    {
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private boolean validateForm()
    {
        String url = txtUrl.getText().toString().trim();
        if(url.equalsIgnoreCase(""))
        {
            return false;
        }

        return true;
    }

    public Link getLinkObject()
    {
        if(currentLink == null)
        {
            currentLink = new Link();
        }

        currentLink.setUrl(txtUrl.getText().toString());
        currentLink.setImportant(chkImportant.isChecked());

        return currentLink;
    }

    private void saveLink()
    {
        if(!validateForm())
        {
            // TODO: 22.08.17 show error
            return;
        }

        Link link = getLinkObject();
        Intent data = new Intent();
        data.putExtra(Link.KEY, link);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, data);
        dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnSaveLink:
                saveLink();
                break;
            case R.id.btnCancelSaveLink:
                dismiss();
                break;
        }
    }
}
