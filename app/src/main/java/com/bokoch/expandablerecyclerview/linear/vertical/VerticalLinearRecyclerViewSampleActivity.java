package com.bokoch.expandablerecyclerview.linear.vertical;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bokoch.expandablerecyclerview.R;

import java.util.Arrays;
import java.util.List;


public class VerticalLinearRecyclerViewSampleActivity extends AppCompatActivity{

        private RecipeAdapter mAdapter;

        @NonNull
        public static Intent newIntent(Context context) {
                return new Intent(context, VerticalLinearRecyclerViewSampleActivity.class);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_recycler_view_sample);

                Ingredient beef = new Ingredient("beef", false);
                Ingredient cheese = new Ingredient("cheese", true);
                Ingredient salsa = new Ingredient("salsa", true);
                Ingredient tortilla = new Ingredient("tortilla", true);
                Ingredient ketchup = new Ingredient("ketchup", true);
                Ingredient bun = new Ingredient("bun", true);

                Recipe taco = new Recipe("taco", Arrays.asList(beef, cheese, salsa, tortilla));
                Recipe quesadilla = new Recipe("quesadilla", Arrays.asList(cheese, tortilla));
                Recipe burger = new Recipe("burger", Arrays.asList(beef, cheese, ketchup, bun));
                final List<Recipe> recipes = Arrays.asList(taco, quesadilla, burger);

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                mAdapter = new RecipeAdapter(this, recipes);
                mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                        @UiThread
                        @Override
                        public void onParentExpanded(int parentPosition) {
                                Recipe expandedRecipe = recipes.get(parentPosition);
                                Log.i("Recipe expanded clicked", expandedRecipe.getName());
                                String toastMsg = getResources().getString(R.string.expanded, expandedRecipe.getName());
                                Toast.makeText(VerticalLinearRecyclerViewSampleActivity.this,
                                        toastMsg,
                                        Toast.LENGTH_SHORT)
                                        .show();
                        }

                        @UiThread
                        @Override
                        public void onParentCollapsed(int parentPosition) {
                                Recipe collapsedRecipe = recipes.get(parentPosition);
                                Log.i("Recipe collapsed clicked", collapsedRecipe.getName());

                                String toastMsg = getResources().getString(R.string.collapsed, collapsedRecipe.getName());
                                Toast.makeText(VerticalLinearRecyclerViewSampleActivity.this,
                                        toastMsg,
                                        Toast.LENGTH_SHORT)
                                        .show();
                        }
                });

                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
                super.onSaveInstanceState(outState);
                mAdapter.onSaveInstanceState(outState);
        }

        @Override
        protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
                super.onRestoreInstanceState(savedInstanceState);
                mAdapter.onRestoreInstanceState(savedInstanceState);
        }
}