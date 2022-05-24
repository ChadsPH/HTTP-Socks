package com.chaddevz.sockshttp.model;

import com.chaddevz.sockshttp.MainActivity;
import androidx.fragment.app.Fragment;
import android.view.View;

public abstract class ViewFragment extends Fragment
	implements OnUpdateLayout
{
	public void updateLayout()
	{
		updateLayout(null);
	}
}
