# Git

## How does the .gitignore work?

The `.gitignore` file plays a crucial role in your Git repository. It tells Git which files or directories to ignore in a project. This is particularly useful for excluding files that don’t need to be committed, such as temporary files, logs, or sensitive information.

### Here’s how it works

#### Patterns: Each line in a .gitignore file specifies a pattern. These patterns determine which files or directories Git should ignore. For example

# Ignore build artifacts

```
build/
```

# Ignore log files

```
*.log
```

# Ignore sensitive config files

```

secret.conf
```

#### Order of Precedence

* Git checks patterns in the following order (highest to lowest precedence):
  * Command-line patterns.
  * Patterns in .gitignore files within the same directory or parent directories (up to the top-level of the working tree).
  * Patterns in $GIT_DIR/info/exclude.
  * Patterns specified by the core.excludesFile configuration variable.
* If a file matches a pattern, it is ignored.

#### Version Control

* Patterns meant to be shared across repositories (e.g., files all developers should ignore) go into a .gitignore file.
* Repository-specific patterns (not shared with other related repositories) can go into $GIT_DIR/info/exclude.

#### Negation

* You can use an optional prefix ! to negate a pattern. Any file excluded by a previous pattern will become included again if matched by a negated pattern.

Remember that .gitignore only affects untracked files. To stop tracking a file that is currently tracked, use git rm --cached to remove it from the index, and then add its filename to .gitignore

## Where does git store all the data?

Git stores its data in a hidden folder named `.git` at the root of your working directory.

Here’s how it works:

### Snapshots

* Git thinks of its data as a set of snapshots of a mini filesystem.
When you commit changes, Git takes a picture of all your files at that moment and stores a reference to that snapshot.
* If files haven’t changed, Git doesn’t store them again; it just links to the previous identical content.
* So, each commit is essentially a snapshot of your project’s state.

### Object Store

* Inside a Git repository, there are two main data structures:
* `Object store`: Git tracks file history by storing the contents of each file as a blob (referenced by a unique SHA1 hash).
* `Index`: Git also maintains an index of file changes.
* These structures reside in the .git folder.

### Efficiency

* Git avoids duplicating content by referencing existing blobs.
* Changes to file content create new blob objects.

In summary, Git’s data model revolves around snapshots, efficient storage, and unique hashes for content identification23.

## What is the cherry-pick command in git?

The git cherry-pick command allows you to selectively apply changes from existing commits to your current branch.

Here’s how it works:

`Usage:` To cherry-pick a specific commit, use:

```bash
git cherry-pick <commit-id>
```

Replace `commit-id` with the hash of the commit you want to apply.

* Scenario:
* Imagine you have a maintenance branch with a bug fix commit, and you want to apply it to your development branch.
* Use git cherry-pick to bring that specific commit into your current branch.

* Result:
Git creates a new commit with the changes from the cherry-picked commit.
If conflicts occur, you’ll need to resolve them manually.

Remember, cherry-picking is useful for integrating specific commits across branches! 🌟 12

## What is the practical use of Git branching?

Branching in Git serves several practical purposes, making it an essential part of the development workflow:

1. Isolation of Work:

* Branches allow developers to work on different features or bug fixes independently.
* Each branch represents a separate line of development, preventing interference with the main project.
* For example, you can create a branch for a new feature, experiment, or bug fix.

2. Collaboration:

* Teams can collaborate effectively by creating branches for specific tasks.
* Developers can work on their branches simultaneously without affecting others.
* Once work is complete, branches can be merged back into the main branch.

3. Feature Development:

* Branches facilitate feature development.
* You can create a branch for a specific feature, develop it, and merge it when ready.
* This keeps the main branch stable while allowing feature development in parallel.

4. Hotfixes and Bug Fixes:

* When a critical bug arises, create a hotfix branch.
* Fix the issue in the hotfix branch and merge it back to the main branch.
* This ensures that urgent fixes don’t disrupt ongoing development.

5. Experimentation and Prototyping:

* Create branches to experiment with new ideas or prototype features.
* If the experiment is successful, merge it into the main branch.
* Otherwise, discard the branch without affecting the main codebase.

In summary, Git branching provides flexibility, organization, and efficient collaboration in software development.

## What is `git stash`?

Certainly! When you want to record the current state of your working directory and index but need to return to a clean working directory, you can use git stash.

### Here’s how it works

* Stashing Changes:
`git stash` saves your local modifications away.

It reverts the working directory to match the HEAD commit.
The modifications stashed away can be listed with git stash list, inspected with git stash show, and restored (potentially on top of a different commit) with git stash apply.

### Usage

Calling `git stash` without any arguments is equivalent to git stash push.
You can give a more descriptive message when creating a stash.

```Text
Remember, stashes are locally scoped and not pushed to the remote repository by git push
```
