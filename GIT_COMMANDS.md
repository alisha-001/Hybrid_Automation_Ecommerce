# Git Commands & Repository Setup

## Quick Reference: All Git Commands

### 1. Initial Setup (Run Once)

```powershell
# Navigate to project directory
cd "c:\Users\My Asus\Music\project_marlabs\automation-testing-framework"

# Initialize local git repository
git init

# Configure git user (if not done globally)
git config user.name "Your Full Name"
git config user.email "your.email@example.com"

# Add all files to git
git add .

# Create initial commit
git commit -m "Initial commit: Comprehensive automation testing framework with Web, API, Mobile, and Windows automation"

# Verify commit
git log
```

### 2. Create Remote Repository

#### GitHub
```powershell
# 1. Go to https://github.com/new
# 2. Repository name: automation-testing-framework
# 3. Description: Comprehensive automation testing framework
# 4. Make it Public (recommended for portfolio)
# 5. Do NOT initialize with README
# 6. Click Create Repository
# 7. Copy the URL (https://github.com/YOUR_USERNAME/automation-testing-framework.git)

# 8. In PowerShell:
git remote add origin https://github.com/YOUR_USERNAME/automation-testing-framework.git
git branch -M main
git push -u origin main
```

#### GitLab
```powershell
# 1. Go to https://gitlab.com/projects/new
# 2. Project name: automation-testing-framework
# 3. Visibility: Public
# 4. Create project
# 5. Copy the URL (https://gitlab.com/YOUR_USERNAME/automation-testing-framework.git)

# 6. In PowerShell:
git remote add origin https://gitlab.com/YOUR_USERNAME/automation-testing-framework.git
git branch -M main
git push -u origin main
```

#### Bitbucket
```powershell
# 1. Go to https://bitbucket.org/repo/create
# 2. Repository name: automation-testing-framework
# 3. Project type: Team-managed
# 4. Create repository
# 5. Copy the URL

# 6. In PowerShell:
git remote add origin https://bitbucket.org/YOUR_USERNAME/automation-testing-framework.git
git branch -M main
git push -u origin main
```

### 3. Verify Remote Connection

```powershell
# Check remote repositories
git remote -v

# Should output:
# origin  https://github.com/YOUR_USERNAME/automation-testing-framework.git (fetch)
# origin  https://github.com/YOUR_USERNAME/automation-testing-framework.git (push)
```

### 4. Push to Repository

```powershell
# First time push (includes -u to set upstream)
git push -u origin main

# Subsequent pushes
git push
```

## Regular Git Workflow

### Before Making Changes
```powershell
# Get latest changes from repository
git pull origin main
```

### Make Changes to Code
```powershell
# Edit files in your IDE/editor
# Example: Add new test class, update page object, etc.
```

### Commit Changes
```powershell
# Check status (shows changed files)
git status

# Stage all changes
git add .

# Or stage specific files
git add src/test/java/com/automationframework/web/NewTest.java

# Commit with meaningful message
git commit -m "Add new test for product search functionality"

# Or commit specific files
git commit -m "Update LoginPage with new locators" src/main/java/com/automationframework/pages/LoginPage.java
```

### Push Changes
```powershell
# Push to remote repository
git push origin main

# Or simply (if main is already set as default branch)
git push
```

## Useful Git Commands

### View History
```powershell
# View commit log
git log

# View last 5 commits
git log -5

# View commits in one line
git log --oneline

# View commits with graph
git log --graph --oneline --all
```

### Branching (For Multiple Features)
```powershell
# Create new branch
git checkout -b feature/new-test-scenario

# Work on feature branch
# Make changes and commit

# Switch back to main
git checkout main

# Merge feature branch
git merge feature/new-test-scenario

# Delete feature branch
git branch -d feature/new-test-scenario

# Push merged changes
git push origin main
```

### Undo Changes
```powershell
# Undo uncommitted changes in a file
git checkout -- src/path/to/file.java

# Undo uncommitted changes in all files
git checkout -- .

# Undo last commit (keep changes)
git reset --soft HEAD~1

# Undo last commit (discard changes)
git reset --hard HEAD~1
```

### Tagging Releases
```powershell
# Create a tag for release version
git tag -a v1.0.0 -m "Release version 1.0.0 - Initial framework release"

# List tags
git tag

# Push tags to repository
git push origin --tags
```

## Commit Message Best Practices

```bash
# Good commit messages:
git commit -m "Add user registration test for automationexercise.com"
git commit -m "Implement WaitUtility with explicit wait methods"
git commit -m "Update APIHelper to support PATCH requests"
git commit -m "Fix element not found issue in HomePage.java"
git commit -m "Configure ExtentReports for HTML test reports"

# Bad commit messages:
git commit -m "fixed bug"              # Too vague
git commit -m "Updated stuff"          # Not specific
git commit -m "asdf"                   # Meaningless
```

## Collaborative Workflow (Team Development)

### Pull Request / Merge Request Workflow

```powershell
# 1. Create feature branch
git checkout -b feature/new-mobile-test

# 2. Make changes and commits
git add .
git commit -m "Add mobile calculator automation tests"

# 3. Push feature branch
git push origin feature/new-mobile-test

# 4. On GitHub/GitLab, create Pull Request/Merge Request
# - Go to repository website
# - Create PR from feature/new-mobile-test to main
# - Add description of changes
# - Request reviewers

# 5. After review and approval, merge through web interface
# OR merge locally:
git checkout main
git pull origin main
git merge feature/new-mobile-test
git push origin main
```

## Troubleshooting

### Issue: "fatal: not a git repository"
```powershell
# Solution: Make sure you're in correct directory
cd "c:\Users\My Asus\Music\project_marlabs\automation-testing-framework"

# If not initialized, run:
git init
```

### Issue: "error: src refspec main does not match any"
```powershell
# Solution: Rename branch to main
git branch -M main

# Or create initial commit
git add .
git commit -m "Initial commit"
```

### Issue: "fatal: remote origin already exists"
```powershell
# Solution: If adding remote that already exists
# Remove existing remote
git remote remove origin

# Then add new remote
git remote add origin https://github.com/YOUR_USERNAME/automation-testing-framework.git
```

### Issue: "Permission denied (publickey)"
```powershell
# Solution: Use HTTPS instead of SSH, or setup SSH keys
# Or use GitHub Personal Access Token

# Generate token at: https://github.com/settings/tokens
# Use token as password when prompted

# Store credentials (optional)
git config --global credential.helper store
```

### Issue: Authentication fails
```powershell
# Try clearing stored credentials
git credential reject https://github.com

# Try again - will prompt for username and password/token
git push

# Or use credential helper
git config --global credential.helper cache
```

## Useful Git Aliases

```powershell
# Create shortcuts for common commands
git config --global alias.st status
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit
git config --global alias.lo "log --oneline"
git config --global alias.loa "log --oneline --all"

# Now you can use:
git st      # instead of git status
git co main # instead of git checkout main
git ci -m   # instead of git commit -m
```

## Complete Initial Push Example

```powershell
# Step 1: Navigate to project
cd "c:\Users\My Asus\Music\project_marlabs\automation-testing-framework"

# Step 2: Initialize and commit
git init
git add .
git commit -m "Initial commit: Comprehensive automation framework"

# Step 3: Create remote repository on GitHub

# Step 4: Add remote and push
git remote add origin https://github.com/YOUR_USERNAME/automation-testing-framework.git
git branch -M main
git push -u origin main

# Step 5: Verify
git remote -v
git log --oneline
```

## GitHub Repository URL Format

Once pushed, your repository will be at:
```
https://github.com/YOUR_USERNAME/automation-testing-framework
```

You can share this URL with:
- Team members
- Recruiters
- Stakeholders

## Additional Resources

- [Git Documentation](https://git-scm.com/doc)
- [GitHub Guides](https://guides.github.com/)
- [GitLab Documentation](https://docs.gitlab.com/)
- [Bitbucket Tutorials](https://www.atlassian.com/git/tutorials/bitbucket-cloud)
- [Pro Git Book](https://git-scm.com/book/en/v2)

---

**Remember:** 
- Always pull before pushing
- Write clear commit messages
- Commit frequently with logical changes
- Use branches for features
- Don't commit sensitive data (use .gitignore)

Good luck with your automation framework! 🚀
