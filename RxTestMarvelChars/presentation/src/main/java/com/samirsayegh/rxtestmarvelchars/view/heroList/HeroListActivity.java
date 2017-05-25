package com.samirsayegh.rxtestmarvelchars.view.heroList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samirsayegh.rxtestmarvelchars.R;
import com.samirsayegh.rxtestmarvelchars.dataInjector.components.CharactersComponent;
import com.samirsayegh.rxtestmarvelchars.dataInjector.components.DaggerCharactersComponent;
import com.samirsayegh.rxtestmarvelchars.domain.entities.Hero;
import com.samirsayegh.rxtestmarvelchars.presenter.heroList.CharacterListPresenter;
import com.samirsayegh.rxtestmarvelchars.view.base.BaseActivity;
import com.samirsayegh.rxtestmarvelchars.view.components.RecyclerScrollListener;
import com.samirsayegh.rxtestmarvelchars.view.components.adapter.MainAdapter;
import com.samirsayegh.rxtestmarvelchars.view.components.adapter.MainAdapterListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HeroListActivity extends BaseActivity implements HeroListView, MainAdapterListener {

    @Inject
    CharacterListPresenter characterListPresenter;

    private MainAdapter mainAdapter;
    private RecyclerScrollListener recyclerScrollListener;
    private CharactersComponent characterComponent;

    @BindView(R.id.recyclerViewMain)
    RecyclerView recyclerView;

    public HeroListActivity() {
        layoutId = R.layout.activity_hero_list;
    }

    @Override
    protected void init() {
        initializeInjector();
        initRecyclerView();
        initializePresenter();
    }

    private void initializePresenter() {
        setPresenter(characterListPresenter);
        characterListPresenter.setView(this);
        characterListPresenter.initialize();
    }

    private void initializeInjector() {
        characterComponent = DaggerCharactersComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        characterComponent.inject(this);
    }

    private void initRecyclerView() {
        mainAdapter = new MainAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerScrollListener = new RecyclerScrollListener(linearLayoutManager, this);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addOnScrollListener(recyclerScrollListener);
    }

    @Override
    public void listLoaded(List<Hero> heroList) {
        mainAdapter.setNewList(heroList);
        mainAdapter.notifyDataSetChanged();
        recyclerScrollListener.loaded();
    }

    @Override
    public void listUpdated(List<Hero> heroList) {
        mainAdapter.setAddToList(heroList);
        mainAdapter.notifyDataSetChanged();
        recyclerScrollListener.loaded();
    }

    @Override
    public boolean isLoadingMoreItems() {
        return recyclerScrollListener.isLoading();
    }

    @Override
    public void stopLoading() {
        recyclerScrollListener.loaded();
    }

    @Override
    public void loadMoreElements() {
        characterListPresenter.loadMoreElements();
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildLayoutPosition(v);
        Hero hero = mainAdapter.getHero(position);
        navigateWithExtra(EXTRA_HERO, hero);
    }
}
