# Cards - Android

A card playing simulator for Android.

## Help me, please?

This is a simple example illustrating some trouble I'm having with Android's FragmentManager. The Main Activity has a simple DrawerLayout that lets you pick from three views "Your  Hand", "Your Table" and "Opponent's Hand". When one of these three items is clicked, the main Activity's lone FrameLayout is replaced with a fragment representing the desired view:

```java
    private void navigateToDrawerSelection(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = HandFragment.newInstance(mGame.getHand());
                break;
            case 1:
                fragment = HandFragment.newInstance(mGame.getTable());
                break;
            case 2:
                fragment = HandFragment.newInstance(mGame.getOpponentHand());
                break;
        }

        // Insert the fragment by replacing any existing fragment
       FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

```

The problem is only the **first** transaction results in the desired Fragment becoming visible... I know I'm missing something obvious...
