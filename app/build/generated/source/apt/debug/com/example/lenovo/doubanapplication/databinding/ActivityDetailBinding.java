package com.example.lenovo.doubanapplication.databinding;
import com.example.lenovo.doubanapplication.R;
import com.example.lenovo.doubanapplication.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.image_button2, 1);
        sViewsWithIds.put(R.id.title_text, 2);
        sViewsWithIds.put(R.id.id_text, 3);
        sViewsWithIds.put(R.id.language_text, 4);
        sViewsWithIds.put(R.id.country_text, 5);
        sViewsWithIds.put(R.id.image_view, 6);
        sViewsWithIds.put(R.id.detail_text, 7);
        sViewsWithIds.put(R.id.image_button, 8);
    }
    // views
    @NonNull
    public final android.widget.TextView countryText;
    @NonNull
    public final android.widget.TextView detailText;
    @NonNull
    public final android.widget.TextView idText;
    @NonNull
    public final android.widget.ImageButton imageButton;
    @NonNull
    public final android.widget.ImageButton imageButton2;
    @NonNull
    public final android.widget.ImageView imageView;
    @NonNull
    public final android.widget.TextView languageText;
    @NonNull
    public final android.widget.ScrollView scrollView;
    @NonNull
    public final android.widget.TextView titleText;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.countryText = (android.widget.TextView) bindings[5];
        this.detailText = (android.widget.TextView) bindings[7];
        this.idText = (android.widget.TextView) bindings[3];
        this.imageButton = (android.widget.ImageButton) bindings[8];
        this.imageButton2 = (android.widget.ImageButton) bindings[1];
        this.imageView = (android.widget.ImageView) bindings[6];
        this.languageText = (android.widget.TextView) bindings[4];
        this.scrollView = (android.widget.ScrollView) bindings[0];
        this.scrollView.setTag(null);
        this.titleText = (android.widget.TextView) bindings[2];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityDetailBinding>inflate(inflater, com.example.lenovo.doubanapplication.R.layout.activity_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.lenovo.doubanapplication.R.layout.activity_detail, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}