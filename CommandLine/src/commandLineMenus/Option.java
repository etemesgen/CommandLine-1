package commandLineMenus;

import commandLineMenus.rendering.MenuRenderer;

/**
 * Option figurant dans un menu.
 */

public class Option
{
	private static boolean locked = false;
	protected String shortcut;
	private String title;
	protected Action action;
	MenuRenderer menuRenderer = null;

	public Option(String title, String shortcut, Action action)
	{
		this(title, shortcut);
		this.action = action;
	}
	
	/**
	 * Créée une option.
	 * @param title titre de l'option.
	 * @param shortcut raccourci à saisir pour activer l'option.
	 */
	
	public Option(String title, String shortcut)
	{
		this.title = title;
		this.shortcut = shortcut;
	}
	
	/**
	 * Retourne le raccourci permettant de sélectioner cette option.
	 */
	
	public String getShorcut()
	{
		return shortcut;
	}

	/**
	 * Modifie le raccourci permettant de sélectioner cette option.
	 */
	
	void setShortcut(String shortcut)
	{
		this.shortcut = shortcut;
	}

	/**
	 * Retourne le libellé de l'option.
	 */
	
	public String getTitle()
	{
		return title;
	}

	/**
	 * Affecte une action à la sélection de l'option.
	 * @param action l'objet dont la méthode optionSelectionnee() sera 
	 * appelé une fois une option choisie.
	 */
	
	public void setAction(Action action)
	{
		this.action = action;
	}
	
	/**
	 * Retourne l'action associé à la sélection de l'option.
	 * @return l'objet dont la méthode optionSelectionnee() sera 
	 * appelé une fois une option choisie.
	 */
	
	public Action getAction()
	{
		return action;
	}

	protected void lock()
	{
		locked = true;
	}
	
	protected void unlock()
	{
		locked = false;
	}
	
	protected boolean isLocked()
	{
		return locked;
	}
	
	void optionSelected()
	{
		if (action != null)
			action.optionSelectionnee();
	}
	
	public String stringOfOption()
	{
		return menuRenderer.option(getShorcut(), getTitle());
	}

	@Override
	public String toString()
	{
		return getTitle();
	}
	
	public void setRenderer(MenuRenderer menuRenderer)
	{
		if (isLocked())
			throw new ConcurrentModificationException("Impossible to set rendering of "
					+ getTitle() + " while running.");
		this.menuRenderer = menuRenderer;
	}	

	protected void setRenderers(MenuRenderer menuRenderer)
	{
		if (this.menuRenderer == null && menuRenderer != null)
			setRenderer(menuRenderer);
	}

	public class ConcurrentModificationException extends RuntimeException
	{
		private static final long serialVersionUID = 6139684008297267150L;

		public ConcurrentModificationException(String message) 
		{
			super(message);
		}
	}
}