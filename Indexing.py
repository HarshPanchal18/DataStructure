import os

def generate_md_links(root_dir):
    md_content = "<details>\n<summary>Index</summary>\n\n"

    for root, dirs, files in os.walk(root_dir):
        # Exclude hidden directories
        dirs[:] = [d for d in dirs if not d.startswith('.')]

        if files:
            # Extract directory name
            dir_name = os.path.basename(root)

            # Add directory name as a section heading
            md_content += f"<details>\n<summary>{dir_name}</summary>\n\n"

            # Generate Markdown links for files in the directory
            for file in files:
                file_path = os.path.join(root, file)
                relative_path = os.path.relpath(file_path, root_dir)
                md_link = f"[{file}](/{file_path})\n"
                md_content += f"{md_link}"

            md_content += "\n</details>\n\n"

    md_content += "\n</details>"

    return md_content

# Provide the root directory of your folder structure
root_directory = "./"

# Generate Markdown content
markdown_content = generate_md_links(root_directory)

# Write Markdown content to README.md
with open("README.md", "w") as readme_file:
    readme_file.write(markdown_content)

print("README.md updated successfully!")
