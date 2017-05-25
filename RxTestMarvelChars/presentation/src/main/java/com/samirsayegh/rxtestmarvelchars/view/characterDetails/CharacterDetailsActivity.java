package com.samirsayegh.rxtestmarvelchars.view.characterDetails;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.samirsayegh.rxtestmarvelchars.R;
import com.samirsayegh.rxtestmarvelchars.dataInjector.components.CharacterDetailsComponent;
import com.samirsayegh.rxtestmarvelchars.dataInjector.components.DaggerCharacterDetailsComponent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.BaseContent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Thumbnail;
import com.samirsayegh.rxtestmarvelchars.presenter.characterDetails.CharacterDetailsPresenter;
import com.samirsayegh.rxtestmarvelchars.view.base.BaseActivity;
import com.samirsayegh.rxtestmarvelchars.view.characterDetails.adapter.HeroPagerAdapter;
import com.samirsayegh.rxtestmarvelchars.view.characterDetails.fragments.ComicEventsListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.samirsayegh.rxtestmarvelchars.view.characterDetails.adapter.HeroPagerAdapter.COMICS;
import static com.samirsayegh.rxtestmarvelchars.view.characterDetails.adapter.HeroPagerAdapter.EVENTS;

/**
 * Created by yormirsamir.sayegh on 24/05/2017.
 */

public class CharacterDetailsActivity extends BaseActivity implements CharacterDetailsView,
        TabLayout.OnTabSelectedListener, ComicEventsListener {

    @Inject
    CharacterDetailsPresenter presenter;

    private HeroPagerAdapter heroPagerAdapter;
    private Hero hero;
    private CharacterDetailsComponent component;

    @BindView(R.id.textViewDetailsName)
    TextView textViewName;
    @BindView(R.id.textViewDetailsDetail)
    TextView textViewDetails;
    @BindView(R.id.imageViewDetailsHero)
    ImageView imageView;
    @BindView(R.id.tabLayoutDetails)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerDetails)
    ViewPager viewPager;

    public CharacterDetailsActivity() {
        layoutId = R.layout.activity_details;
    }

    @Override
    protected void init() {
        initializeInjector();
        initTabLayout();
        initHero();
        getLists();
    }

    private void initializeInjector() {
        component = DaggerCharacterDetailsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        component.inject(this);
    }

    private void getLists() {
        presenter.setView(this);
        presenter.setCharacterID(hero.getId());
        presenter.initialize();
    }

    private void initHero() {
        if (getIntent().hasExtra(EXTRA_HERO)) {
            hero = (Hero) getIntent().getSerializableExtra(EXTRA_HERO);
            setTitle(hero.getName());
            textViewDetails.setText(hero.getDescription());
            textViewName.setText(hero.getName());
            Picasso.with(this)
                    .load(hero.getThumbnail(Thumbnail.PORTRAIT_LARGE))
                    .into(imageView);
        }
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.comics));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.events));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        heroPagerAdapter = new HeroPagerAdapter(getSupportFragmentManager());
        heroPagerAdapter.setListener(this);
        viewPager.setAdapter(heroPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void comicsLoaded(List<BaseContent> list, int totalComics) {
        tabLayout.getTabAt(COMICS).setText(getResources().getString(R.string.comics_number, totalComics));
        heroPagerAdapter.comicsLoaded(list);
    }

    @Override
    public void comicsUpdated(List<BaseContent> list) {
        heroPagerAdapter.comicsUpdated(list);
    }

    @Override
    public void eventsLoaded(List<BaseContent> list, int totalEvents) {
        heroPagerAdapter.eventsLoaded(list);
        tabLayout.getTabAt(EVENTS).setText(getResources().getString(R.string.events_number, totalEvents));
    }

    @Override
    public void eventsUpdated(List<BaseContent> list) {
        heroPagerAdapter.eventsUpdated(list);
    }

    @Override
    public boolean isLoadingMoreComics() {
        return heroPagerAdapter.isLoadingMoreComics();
    }

    @Override
    public void stopLoadingComics() {
        heroPagerAdapter.stopLoadingComics();
    }

    @Override
    public boolean isLoadingMoreEvents() {
        return heroPagerAdapter.isLoadingMoreEvents();
    }

    @Override
    public void stopLoadingEvents() {
        heroPagerAdapter.stopLoadingEvents();
    }

    @Override
    public void loadMoreElements(int type) {
        if (type == COMICS)
            presenter.loadMoreComics();
        else
            presenter.loadMoreEvents();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
