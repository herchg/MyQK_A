package my.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

/**
 * 
 * @author hermeschang
 * 
 */

public class XViewAnimator extends ViewAnimator {
    
    public interface AnimationListener extends Animation.AnimationListener {
        // do nothing, just the same to Animation.AnimationListener
    }

    private int DEFAULT_VIEW_IN_ANIM_FOR_NEXT = R.anim.slide_in_right;
    private int DEFAULT_VIEW_OUT_ANIM_FOR_NEXT = R.anim.slide_out_left;
    private int DEFAULT_VIEW_IN_ANIM_FOR_PREVIOUS = R.anim.slide_in_left;
    private int DEFAULT_VIEW_OUT_ANIM_FOR_PREVIOUS = R.anim.slide_out_right;
    
    private Animation mViewInAnimForPrevious;
    private Animation mViewOutAnimForNext;
    private Animation mViewInAnimForNext;
    private Animation mViewOutAnimForPrevious;
    
    private AnimationListener mInAnimListener;
    private AnimationListener mOutAnimListener;

    public XViewAnimator(Context context) {
        this(context, null);
    }

    public XViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XViewAnimator);
        mViewInAnimForNext = AnimationUtils.loadAnimation(this.getContext(), a.getResourceId(R.styleable.XViewAnimator_viewInAnimForNext, DEFAULT_VIEW_IN_ANIM_FOR_NEXT));
        mViewOutAnimForNext = AnimationUtils.loadAnimation(this.getContext(), a.getResourceId(R.styleable.XViewAnimator_viewOutAnimForNext, DEFAULT_VIEW_OUT_ANIM_FOR_NEXT));
        mViewInAnimForPrevious = AnimationUtils.loadAnimation(this.getContext(),
                                                          a.getResourceId(R.styleable.XViewAnimator_viewInAnimForPrevious, DEFAULT_VIEW_IN_ANIM_FOR_PREVIOUS));
        mViewOutAnimForPrevious = AnimationUtils.loadAnimation(this.getContext(), a.getResourceId(R.styleable.XViewAnimator_viewOutAnimForPrevious,
                                                                                              DEFAULT_VIEW_OUT_ANIM_FOR_PREVIOUS));
        a.recycle();

        init();
    }

    private void init() {
        
    }
    
    public void setInAnimationListener(AnimationListener inListener) {
        this.mInAnimListener = inListener;
    }

    public void setOutAnimationListener(AnimationListener outListener) {
        this.mOutAnimListener = outListener;
    }

    public boolean next(View child) {

        if (child == null)
            return false;

        for (int i = 0; i < this.getChildCount(); i++) {
            View v = this.getChildAt(i);
            if (v.equals(child)) {
                return false;
            }
        }

        super.addView(child);
        this.setInAnimation(mViewInAnimForNext);
        mViewInAnimForNext.setAnimationListener(this.mInAnimListener);
        this.setOutAnimation(mViewOutAnimForNext);
        mViewOutAnimForNext.setAnimationListener(this.mOutAnimListener);
        super.showNext();
        return true;
    }

    public boolean previous() {
        if (this.getChildCount() > 0) {
            this.setInAnimation(this.mViewInAnimForPrevious);
            mViewInAnimForPrevious.setAnimationListener(this.mInAnimListener);
            this.setOutAnimation(this.mViewOutAnimForPrevious);
            mViewOutAnimForPrevious.setAnimationListener(this.mOutAnimListener);
            
            super.showPrevious();

            this.removeViewAt(this.getChildCount() - 1);

            return true;
        }

        return false;
    }

}
