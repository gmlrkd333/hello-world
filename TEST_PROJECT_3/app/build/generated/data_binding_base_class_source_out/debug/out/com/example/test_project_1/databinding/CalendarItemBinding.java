// Generated by data binding compiler. Do not edit!
package com.example.test_project_1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.test_project_1.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class CalendarItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView calDay;

  @NonNull
  public final TextView calWeek;

  @NonNull
  public final CardView cardCalendar;

  protected CalendarItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView calDay, TextView calWeek, CardView cardCalendar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.calDay = calDay;
    this.calWeek = calWeek;
    this.cardCalendar = cardCalendar;
  }

  @NonNull
  public static CalendarItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.calendar_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static CalendarItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<CalendarItemBinding>inflateInternal(inflater, R.layout.calendar_item, root, attachToRoot, component);
  }

  @NonNull
  public static CalendarItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.calendar_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static CalendarItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<CalendarItemBinding>inflateInternal(inflater, R.layout.calendar_item, null, false, component);
  }

  public static CalendarItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static CalendarItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (CalendarItemBinding)bind(component, view, R.layout.calendar_item);
  }
}
